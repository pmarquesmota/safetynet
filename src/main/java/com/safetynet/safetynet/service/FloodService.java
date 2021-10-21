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

    public List<Flood> getFlood(List<Long> stations) throws NoSuchElementException {
        HashMap<String, List<Personne>> tmpFloods = new HashMap<>();

        stations.stream()
                .map(station ->
                        casernePompierRepository.findById(station).orElseThrow(() ->
                                new NoSuchElementException("La caserne " + station + " n'existe pas")))
                .map(CasernePompier::getAdresses)
                .flatMap(Collection::stream)
                .forEach(adresse -> personneRepository.findAllByAdresse(adresse)
                        .forEach(personne ->
                                tmpFloods.computeIfAbsent(adresse, k -> new ArrayList<>()).add(personne)));

        return tmpFloods
                .keySet()
                .stream()
                .map(s -> new Flood(s, tmpFloods.get(s)))
                .collect(Collectors.toList());
    }
}
