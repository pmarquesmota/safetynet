package com.safetynet.safetynet.service;

import com.safetynet.safetynet.entity.CasernePompier;
import com.safetynet.safetynet.entity.DossierMedical;
import com.safetynet.safetynet.entity.Personne;
import com.safetynet.safetynet.model.Fire;
import com.safetynet.safetynet.model.MedicalRecord;
import com.safetynet.safetynet.model.Person;
import com.safetynet.safetynet.repository.CasernePompierRepository;
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
public class FireServiceTest {
    @Mock
    PersonneRepository personneRepository;

    @Mock
    CasernePompierRepository casernePompierRepository;

    @InjectMocks
    FireService fireService;

    @Test
    public void getFire_shouldReturnOK() {
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
        List<Personne> personnes = List.of(personne);
        when(personneRepository.findAllByAdresse(any())).thenReturn(personnes);

        List<String> adresses = new ArrayList<>();
        adresses.add("1509 Culver St");
        CasernePompier casernePompier = new CasernePompier(1L, adresses);
        List<CasernePompier> casernePompiers = List.of(casernePompier);
        when(casernePompierRepository.findByAdresses(any())).thenReturn(casernePompiers);

        Fire fire = new Fire(personnes, 1L);
        assertEquals(fire, fireService.getFire("1509 Culver St"));

    }
}
