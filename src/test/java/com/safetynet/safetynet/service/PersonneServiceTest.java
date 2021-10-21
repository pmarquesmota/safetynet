package com.safetynet.safetynet.service;

import com.safetynet.safetynet.entity.Personne;
import com.safetynet.safetynet.model.Person;
import com.safetynet.safetynet.repository.PersonneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonneServiceTest {
    @Mock
    PersonneRepository personneRepository;

    @InjectMocks
    PersonneService personneService;

    @Test
    public void getPersonne_shouldReturnOk() {
        Personne personne = new Personne(new Person(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "jaboyd@email.com"
        ));
        when(personneRepository.findById(any())).thenReturn(java.util.Optional.of(personne));

        assertEquals(personne, personneService.getPersonne(1L));

    }

    @Test
    public void getPersonne_shouldReturnNotFound() throws NoSuchElementException {
        when(personneRepository.findById(any())).thenThrow(new NoSuchElementException());
        assertThrows(NoSuchElementException.class, () -> personneService.getPersonne(1L));
    }

    @Test
    public void getPersonnes_shouldReturnOk() {
        List<Personne> personnes = List.of(new Personne(new Person(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "jaboyd@email.com"
        )));
        when(personneRepository.findAll()).thenReturn(personnes);

        assertEquals(personnes, personneService.getPersonnes());

    }

    @Test
    public void addPersonne_shouldReturnOk() {
        Personne personne = new Personne(new Person(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "jaboyd@email.com"
        ));
        when(personneRepository.save(any())).thenReturn(personne);

        assertEquals(personne, personneService.addPersonne(personne));

    }

    @Test
    public void modifyPersonne_shouldReturnOk() {
        Personne personne = new Personne(new Person(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "jaboyd@email.com"
        ));
        when(personneRepository.save(any())).thenReturn(personne);
        when(personneRepository.findById(any())).thenReturn(java.util.Optional.of(personne));

        assertEquals(personne, personneService.modifyPersonne(1L, personne));

    }

    @Test
    public void modifyPersonne_shouldReturnNotFound() {
        Personne personne = new Personne(new Person(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "jaboyd@email.com"
        ));
        when(personneRepository.findById(any())).thenThrow(new NoSuchElementException());

        assertThrows(NoSuchElementException.class, () -> personneService.modifyPersonne(1L, personne));

    }

    @Test
    public void deletePersonne_shouldReturnOk() {
        when(personneRepository.existsById(any())).thenReturn(true);

        personneService.deletePersonne(1L);
        verify(personneRepository).deleteById(any());
    }
}
