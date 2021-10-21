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

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonInfoServiceTest {
    @Mock
    PersonneRepository personneRepository;

    @InjectMocks
    PersonInfoService personInfoService;

    @Test
    public void getPersonInfo_shouldReturnOK() {
        Person person1 = new Person("John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "jaboyd@email.com"
        );
        List<Personne> personnes1 = List.of(new Personne(person1));

        when(personneRepository.findByPrenomAndNom(any(), any())).thenReturn(personnes1);

        assertEquals(personnes1, personInfoService.getPersonInfo("John", "Boyd"));

    }
}
