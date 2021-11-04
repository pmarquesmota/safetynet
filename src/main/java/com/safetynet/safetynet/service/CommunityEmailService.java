package com.safetynet.safetynet.service;

import com.safetynet.safetynet.entity.Personne;
import com.safetynet.safetynet.model.Email;
import com.safetynet.safetynet.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommunityEmailService {
    @Autowired
    PersonneRepository personneRepository;

    // Get emails from people living at city
    public List<Email> getCommunityEmailService(String city) {
        List<Personne> personnes = personneRepository.findByVille(city);

        // Remove duplicates
        // https://stackoverflow.com/questions/29670116/remove-duplicates-from-a-list-of-objects-based-on-property-in-java-8
        Set<String> set = new HashSet<>(personnes.size());

        List<Email> emails = personnes
                .stream()
                .filter(personne -> personne.getVille().equals(city))
                .filter(personne -> set.add(personne.getEmail()))
                .map(p -> new Email(p.getEmail()))
                .collect(Collectors.toList());
        return emails;
    }
}
