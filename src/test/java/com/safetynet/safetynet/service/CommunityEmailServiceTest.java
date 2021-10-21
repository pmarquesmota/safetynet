package com.safetynet.safetynet.service;

import com.safetynet.safetynet.entity.Personne;
import com.safetynet.safetynet.model.Email;
import com.safetynet.safetynet.model.Person;
import com.safetynet.safetynet.repository.PersonneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommunityEmailServiceTest {
    @Mock
    PersonneRepository personneRepository;

    @InjectMocks
    CommunityEmailService communityEmailService;

    @Test
    public void getCommunityEmailService_shouldReturnOK() {
        Personne personne = new Personne(new Person(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "jaboyd@email.com"
        ));
        List<Personne> personnes = List.of(personne);
        when(personneRepository.findByVille(any())).thenReturn(personnes);

        List<Email> emails = List.of(new Email("jaboyd@email.com"));
        assertEquals(emails, communityEmailService.getCommunityEmailService("Culver"));

    }

    @Test
    public void getCommunityEmailService_shouldReturnNull() {
        Personne personne = new Personne(new Person(
                "John",
                "Boyd",
                "1509 Culver St",
                "Paris",
                "97451",
                "841-874-6512",
                "jaboyd@email.com"
        ));
        List<Personne> personnes = List.of(personne);
        when(personneRepository.findByVille(any())).thenReturn(personnes);

        List<Email> emails = new ArrayList<>();
        List<Email> emails_to_find = communityEmailService.getCommunityEmailService("Culver");
        assertEquals(emails, emails_to_find);

    }
}
