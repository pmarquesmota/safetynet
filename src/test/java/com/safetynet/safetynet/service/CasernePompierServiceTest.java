package com.safetynet.safetynet.service;

import com.safetynet.safetynet.entity.CasernePompier;
import com.safetynet.safetynet.entity.DossierMedical;
import com.safetynet.safetynet.entity.Personne;
import com.safetynet.safetynet.model.MedicalRecord;
import com.safetynet.safetynet.model.Person;
import com.safetynet.safetynet.model.StationNumber;
import com.safetynet.safetynet.repository.BirthdayRepository;
import com.safetynet.safetynet.repository.CasernePompierRepository;
import com.safetynet.safetynet.repository.PersonneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CasernePompierServiceTest {

    @Mock
    PersonneRepository personneRepository;
    @Mock
    CasernePompierRepository casernePompierRepository;
    @Mock
    BirthdayRepository birthdayRepository;

    @InjectMocks
    CasernePompierService casernePompierService;

    @Test
    public void getCaserne_shouldReturnOK() {
        List<String> adresses = new ArrayList<>();
        adresses.add("644 Gershwin Cir");
        adresses.add("908 73rd St");
        adresses.add("947 E. Rose Dr");
        CasernePompier casernePompier = new CasernePompier(1L, adresses);

        when(casernePompierRepository.findById(1L)).thenReturn(java.util.Optional.of(casernePompier));

        assertEquals(casernePompier, casernePompierService.getCaserne(1L));
    }

    @Test
    public void getCaserne_shouldReturnNotFound() throws NoSuchElementException {
        when(casernePompierRepository.findById(any())).thenThrow(new NoSuchElementException());
        assertThrows(NoSuchElementException.class, () -> casernePompierService.getCaserne(1L));
    }

    @Test
    public void getStationNumber_shouldReturnNotFound() throws NoSuchElementException {
        when(casernePompierRepository.findById(any())).thenThrow(new NoSuchElementException());
        assertThrows(NoSuchElementException.class, () -> casernePompierService.getStationNumber(1L));
    }

    @Test
    public void getStationNumber_shouldReturnOK() {

        List<String> adresses = List.of("1509 Culver St");
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
        personne.setDossierMedical(new DossierMedical(new MedicalRecord("", "", "03/06/1984", new String[0], new String[0])));

        List<Personne> personnes = List.of(personne);
        when(personneRepository.findAllByAdresse(any())).thenReturn(personnes);

        StationNumber stationNumber = new StationNumber(personnes, 1, 0);

        assertEquals(stationNumber, casernePompierService.getStationNumber(1L));
    }

    @Test
    public void getCasernes_shouldReturnOk() {
        List<String> adresses = List.of("1509 Culver St");
        CasernePompier casernePompier = new CasernePompier(1L, adresses);
        List<CasernePompier> casernePompiers = List.of(casernePompier);
        when(casernePompierRepository.findAll()).thenReturn(casernePompiers);

        assertEquals(casernePompiers, casernePompierService.getCasernes());

    }

    @Test
    public void getCasernes_shouldReturnNull() {
        when(casernePompierRepository.findAll()).thenReturn(null);

        assertNull(casernePompierService.getCasernes());

    }

    @Test
    public void addCaserne_shouldReturnOk() {
        List<String> adresses = List.of("1509 Culver St");
        CasernePompier casernePompier = new CasernePompier(1L, adresses);
        when(casernePompierRepository.save(any())).thenReturn(casernePompier);

        assertEquals(casernePompier, casernePompierService.addCaserne(casernePompier));

    }

    @Test
    public void modifyCaserne_shouldReturnOk() {
        List<String> adresses = List.of("1509 Culver St");
        CasernePompier casernePompier = new CasernePompier(1L, adresses);
        when(casernePompierRepository.findById(any())).thenReturn(java.util.Optional.of(casernePompier));
        when(casernePompierRepository.save(any())).thenReturn(casernePompier);

        assertEquals(casernePompier, casernePompierService.modifyCaserne(1L, casernePompier));

    }

    @Test
    public void modifyCaserne_shouldReturnNotFound() {
        List<String> adresses = List.of("1509 Culver St");
        CasernePompier casernePompier = new CasernePompier(1L, adresses);
        when(casernePompierRepository.findById(any())).thenThrow(new NoSuchElementException());

        assertThrows(NoSuchElementException.class, () -> casernePompierService.modifyCaserne(1L, casernePompier));

    }

    @Test
    public void deleteCaserne_shouldReturnOk() {
        when(casernePompierRepository.existsById(any())).thenReturn(true);

        casernePompierService.deleteCaserne(1L);
        verify(casernePompierRepository).deleteById(any());
    }
}
