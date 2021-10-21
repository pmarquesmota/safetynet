package com.safetynet.safetynet.service;

import com.safetynet.safetynet.entity.CasernePompier;
import com.safetynet.safetynet.entity.Personne;
import com.safetynet.safetynet.model.Person;
import com.safetynet.safetynet.model.PhoneAlert;
import com.safetynet.safetynet.repository.CasernePompierRepository;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PhoneAlertServiceTest {
    @Mock
    PersonneRepository personneRepository;
    @Mock
    CasernePompierRepository casernePompierRepository;

    @InjectMocks
    PhoneAlertService phoneAlertService;
    @Mock
    CasernePompierService casernePompierService;

    @Test
    public void getPhoneAlert_shouldReturnOK() {
        List<String> adresses = List.of("1509 Culver St");
        CasernePompier casernePompier = new CasernePompier(1L, adresses);
        when(casernePompierService.getCaserne(1L)).thenReturn(casernePompier);

        Person person = new Person("John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "jaboyd@email.com"
        );
        List<Personne> personnes = List.of(new Personne(person));
        when(casernePompierService.initPersonnes(any())).thenReturn(personnes);

        PhoneAlert phoneAlert = new PhoneAlert(List.of("841-874-6512"));
        PhoneAlert phoneAlert_to_find = phoneAlertService.getPhoneAlert(1L);
        assertEquals(phoneAlert, phoneAlert_to_find);
    }

    @Test
    public void getPhoneAlert_shouldReturnNotFound() {
        when(casernePompierService.getCaserne(any())).thenThrow(new NoSuchElementException());
        assertThrows(NoSuchElementException.class, () -> phoneAlertService.getPhoneAlert(1L));

    }
}
