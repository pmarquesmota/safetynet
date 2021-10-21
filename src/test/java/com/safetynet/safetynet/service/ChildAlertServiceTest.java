package com.safetynet.safetynet.service;

import com.safetynet.safetynet.entity.DossierMedical;
import com.safetynet.safetynet.entity.Personne;
import com.safetynet.safetynet.model.Child;
import com.safetynet.safetynet.model.ChildAlert;
import com.safetynet.safetynet.model.MedicalRecord;
import com.safetynet.safetynet.model.Person;
import com.safetynet.safetynet.repository.BirthdayRepository;
import com.safetynet.safetynet.repository.PersonneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChildAlertServiceTest {
    @Mock
    PersonneRepository personneRepository;

    @InjectMocks
    ChildAlertService childAlertService;

    @Mock
    BirthdayRepository birthdayRepository;

    @Test
    public void getChildAlert_shouldReturnAdult() throws ParseException {
        Personne personne = new Personne(new Person(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "jaboyd@email.com"
        ));
        DossierMedical dossierMedical = new DossierMedical(new MedicalRecord(
                "John",
                "Boyd",
                "03/06/1984",
                new String[]{"aznol:350mg", "hydrapermazol:100mg"},
                new String[]{"nillacilan"}
        ));
        personne.setDossierMedical(dossierMedical);
        List<Personne> personnes = List.of(personne);
        ChildAlert childAlert = new ChildAlert(new ArrayList<>(), personnes);
        when(personneRepository.findAllByAdresse(any())).thenReturn(personnes);
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse("03/06/1984");
        when(birthdayRepository.getAge(any())).thenReturn(25L);

        ChildAlert childAlert_to_find = childAlertService.getChildAlert("1509 Culver St");
        assertEquals(childAlert, childAlert_to_find);
    }

    @Test
    public void getChildAlert_shouldReturnChild() {
        Personne personne = new Personne(new Person(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "jaboyd@email.com"
        ));
        DossierMedical dossierMedical = new DossierMedical(new MedicalRecord(
                "John",
                "Boyd",
                "03/06/1984",
                new String[]{"aznol:350mg", "hydrapermazol:100mg"},
                new String[]{"nillacilan"}
        ));
        personne.setDossierMedical(dossierMedical);
        List<Personne> personnes = List.of(personne);
        Child child = new Child("John", "Boyd", 5L);
        List<Child> children = List.of(child);
        ChildAlert childAlert = new ChildAlert(children, new ArrayList<>());
        when(personneRepository.findAllByAdresse(any())).thenReturn(personnes);
        when(birthdayRepository.getAge(any())).thenReturn(5L);

        ChildAlert childAlert_to_find = childAlertService.getChildAlert("1509 Culver St");
        assertEquals(childAlert, childAlert_to_find);
    }
}
