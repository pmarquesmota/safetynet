package com.safetynet.safetynet.service;

import com.safetynet.safetynet.entity.Personne;
import com.safetynet.safetynet.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonInfoService {
    @Autowired
    PersonneRepository personneRepository;

    public List<Personne> getPersonInfo(String firstName, String lastName) {
        return personneRepository.findByPrenomAndNom(firstName, lastName);
    }
}
