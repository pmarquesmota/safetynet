package com.safetynet.safetynet.service;

import com.safetynet.safetynet.entity.Personne;
import com.safetynet.safetynet.model.Child;
import com.safetynet.safetynet.model.ChildAlert;
import com.safetynet.safetynet.repository.BirthdayRepository;
import com.safetynet.safetynet.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChildAlertService {
    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private BirthdayRepository birthdayRepository;

    // Return a list of children and adults living at the address
    public ChildAlert getChildAlert(String address) {

        List<Child> enfants = personneRepository
                // get all people living at the address
                .findAllByAdresse(address)
                .stream()
                // only keep children
                .filter(p ->
                        birthdayRepository.getAge(p.getDossierMedical().getDateNaissance()) < 18
                )
                // Create objects from result
                .map(p -> new Child(
                        p.getPrenom(),
                        p.getNom(),
                        birthdayRepository.getAge(p.getDossierMedical().getDateNaissance())

                ))
                // Create List from the result
                .collect(Collectors.toList());

        List<Personne> autres = personneRepository
                .findAllByAdresse(address)
                .stream()
                .filter(p ->
                        birthdayRepository.getAge(p.getDossierMedical().getDateNaissance()) >= 18
                )
                .collect(Collectors.toList());

        return new ChildAlert(enfants, autres);
    }

}
