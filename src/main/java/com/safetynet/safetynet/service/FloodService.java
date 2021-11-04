package com.safetynet.safetynet.service;

import com.safetynet.safetynet.entity.CasernePompier;
import com.safetynet.safetynet.entity.Personne;
import com.safetynet.safetynet.model.Flood;
import com.safetynet.safetynet.repository.CasernePompierRepository;
import com.safetynet.safetynet.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FloodService {
    @Autowired
    CasernePompierRepository casernePompierRepository;

    @Autowired
    PersonneRepository personneRepository;

    //Return the list of people living at the caserns
    public List<Flood> getFlood(List<Long> stations) throws NoSuchElementException {
        HashMap<String, List<Personne>> tmpFloods = new HashMap<>();

        stations.stream()
                //Get the caserns objects
                .map(station ->
                        casernePompierRepository.findById(station).orElseThrow(() ->
                                new NoSuchElementException("La caserne " + station + " n'existe pas")))
                // Get the addresses from the objects
                .map(CasernePompier::getAdresses)
                // Flatten the list of lists to a list
                .flatMap(Collection::stream)
                // Get the people living at the addresses
                .forEach(adresse -> personneRepository.findAllByAdresse(adresse)
                        // Add them to the hash table if they aren't already there
                        .forEach(personne ->
                                tmpFloods.computeIfAbsent(adresse, k -> new ArrayList<>()).add(personne)));

        // Create the resulting object list
        return tmpFloods
                // Extract the keys of the hash table
                .keySet()
                .stream()
                // Create new object from the key and value
                .map(s -> new Flood(s, tmpFloods.get(s)))
                // Turn into a List
                .collect(Collectors.toList());
    }
}
