package com.safetynet.safetynet.service;

import com.safetynet.safetynet.entity.Personne;
import com.safetynet.safetynet.model.Child;
import com.safetynet.safetynet.model.ChildAlert;
import com.safetynet.safetynet.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChildAlertService {
    @Autowired
    PersonneRepository personneRepository;

    public ChildAlert getChildAlert(String address) {

        List<Child> enfants = personneRepository
                .findAllByAdresse(address)
                .stream()
                .filter(p ->
                        Birthday.getAge(p.getDossierMedical().getDateNaissance()) < 18
                )
                .map(p -> new Child(
                        p.getPrenom(),
                        p.getNom(),
                        Birthday.getAge(p.getDossierMedical().getDateNaissance())

                ))
                .collect(Collectors.toList());

        List<Personne> autres = personneRepository
                .findAllByAdresse(address)
                .stream()
                .filter(p ->
                        Birthday.getAge(p.getDossierMedical().getDateNaissance()) > 18
                )
                .collect(Collectors.toList());

        return new ChildAlert(enfants, autres);
    }

}
