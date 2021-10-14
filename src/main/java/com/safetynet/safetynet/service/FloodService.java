package com.safetynet.safetynet.service;

import com.safetynet.safetynet.entity.CasernePompier;
import com.safetynet.safetynet.entity.Personne;
import com.safetynet.safetynet.model.Flood;
import com.safetynet.safetynet.repository.CasernePompierRepository;
import com.safetynet.safetynet.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class FloodService {
    @Autowired
    CasernePompierRepository casernePompierRepository;

    @Autowired
    PersonneRepository personneRepository;

    public List<Flood> getFlood(List<Long> stations) throws NoSuchElementException {
        List<Flood> floods = new ArrayList<>();

        stations.forEach(station -> {
            CasernePompier casernePompier = casernePompierRepository.findById(station).orElse(null);
            casernePompier.getAdresses().forEach(adresse -> {
                List<Personne> personnes = personneRepository.findAllByAdresse(adresse);
                personnes.forEach(personne -> {
                    AtomicReference<Boolean> found = new AtomicReference<>(false);
                    floods.forEach(flood -> {
                        if (flood.getAdresse().equals(personne.getAdresse())) {
                            flood.getPersonnes().add(personne);
                            found.set(true);
                        }
                    });
                    if (!found.get()) {
                        List<Personne> tmpPersonnes = new ArrayList<>();
                        tmpPersonnes.add(personne);
                        Flood flood = new Flood(personne.getAdresse(), tmpPersonnes);
                        floods.add(flood);
                    }
                });
            });
        });
        return floods;
    }
}
