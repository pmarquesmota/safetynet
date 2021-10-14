package com.safetynet.safetynet.service;

import com.safetynet.safetynet.entity.CasernePompier;
import com.safetynet.safetynet.entity.DossierMedical;
import com.safetynet.safetynet.entity.Personne;
import com.safetynet.safetynet.model.MedicalRecord;
import com.safetynet.safetynet.model.Person;
import com.safetynet.safetynet.model.StationNumber;
import com.safetynet.safetynet.repository.CasernePompierRepository;
import com.safetynet.safetynet.repository.PersonneRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CasernePompierServiceTest {

    @Autowired
    PersonneRepository personneRepository;
    @Autowired
    private CasernePompierRepository casernePompierRepository;

    @Test
    public void getCaserne_shouldReturnOK() throws Exception {
        List<String> adresses = new ArrayList<>();
        adresses.add("644 Gershwin Cir");
        adresses.add("908 73rd St");
        adresses.add("947 E. Rose Dr");
        CasernePompier casernePompier = new CasernePompier(1L, adresses);

        when(casernePompierRepository.findById(1L)).thenReturn(java.util.Optional.of(casernePompier));

        CasernePompierService casernePompierService = new CasernePompierService();
        assertTrue(casernePompier.equals(casernePompierService.getCaserne(1L)));
    }

    @Test(expected = NoSuchElementException.class)
    public void getCaserne_shouldReturnNotFound() throws NoSuchElementException {
        when(casernePompierRepository.findById(any())).thenThrow(new NoSuchElementException());
        CasernePompierService casernePompierService = new CasernePompierService();
        casernePompierService.getCaserne(1L);
    }

    @Test(expected = NoSuchElementException.class)
    public void getStationNumber_shouldReturnNotFound() throws NoSuchElementException {
        when(casernePompierRepository.findById(any())).thenThrow(new NoSuchElementException());
        CasernePompierService casernePompierService = new CasernePompierService();
        casernePompierService.getStationNumber(1L);
    }

    @Test
    public void getStationNumber_shouldReturnOK() throws Exception {
        List<String> adresses = Arrays.asList("1509 Culver St");
        CasernePompier casernePompier = new CasernePompier(1L, adresses);
        when(casernePompierRepository.findById(1L)).thenReturn(java.util.Optional.of(casernePompier));

        Person person = new Person("John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "jaboyd@email.com"
        );
        Personne personne = new Personne(person);

        MedicalRecord medicalRecord = new MedicalRecord("John",
                "Boyd",
                "03/06/1984",
                new String[]{"aznol:350mg", "hydrapermazol:100mg"},
                new String[]{"nillacilan"}
        );
        personne.setDossierMedical(new DossierMedical(medicalRecord));

        List<Personne> personnes = Arrays.asList(personne);
        when(personneRepository.findAllByAdresse(any())).thenReturn(personnes);

        StationNumber stationNumber = new StationNumber(personnes, 1, 0);

        CasernePompierService casernePompierService = new CasernePompierService();
        assertTrue(stationNumber.equals(casernePompierService.getStationNumber(1L)));
    }

}
