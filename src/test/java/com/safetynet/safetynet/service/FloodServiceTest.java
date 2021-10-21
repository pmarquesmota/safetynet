package com.safetynet.safetynet.service;

import com.safetynet.safetynet.entity.CasernePompier;
import com.safetynet.safetynet.entity.Personne;
import com.safetynet.safetynet.model.Flood;
import com.safetynet.safetynet.model.Person;
import com.safetynet.safetynet.repository.CasernePompierRepository;
import com.safetynet.safetynet.repository.PersonneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FloodServiceTest {
    @Mock
    CasernePompierRepository casernePompierRepository;

    @Mock
    PersonneRepository personneRepository;

    @InjectMocks
    FloodService floodService;

    @Test
    public void getFlood_shouldReturnOK() {
        Person person1 = new Person("John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "jaboyd@email.com"
        );
        List<Personne> personnes1 = List.of(new Personne(person1));

        Person person2 = new Person("Jonanathan",
                "Marrack",
                "29 15th St",
                "Culver",
                "97451",
                "841-874-6513",
                "drk@email.com"
        );
        List<Personne> personnes2 = List.of(new Personne(person2));

        List<Flood> floods = Arrays.asList(
                new Flood("1509 Culver St", personnes1),
                new Flood("29 15th St", personnes2)
        );
        List<String> adresses1 = new ArrayList<>();
        adresses1.add("1509 Culver St");
        CasernePompier casernePompier1 = new CasernePompier(1L, adresses1);
        List<String> adresses2 = new ArrayList<>();
        adresses2.add("29 15th St");
        CasernePompier casernePompier2 = new CasernePompier(2L, adresses2);

        when(casernePompierRepository.findById(1L)).thenReturn(java.util.Optional.of(casernePompier1));
        when(casernePompierRepository.findById(2L)).thenReturn(java.util.Optional.of(casernePompier2));

        when(personneRepository.findAllByAdresse("1509 Culver St")).thenReturn(personnes1);
        when(personneRepository.findAllByAdresse("29 15th St")).thenReturn(personnes2);

        List<Flood> floods_to_find = floodService.getFlood(Arrays.asList(1L, 2L));
        Assertions.assertEquals(floods, floods_to_find);

    }

    @Test
    public void getFlood_shouldReturnNotFound() {
        when(casernePompierRepository.findById(any())).thenThrow(new NoSuchElementException());
        assertThrows(NoSuchElementException.class, () -> floodService.getFlood(Arrays.asList(1L, 2L)));
    }
}
