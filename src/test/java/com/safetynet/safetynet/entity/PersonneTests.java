package com.safetynet.safetynet.entity;

import com.safetynet.safetynet.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PersonneTests {
    @Test
    void PersonneTest() {
        Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
        Personne personne = new Personne(person);

        Assertions.assertEquals(personne.getPrenom(), "John");
        Assertions.assertEquals(personne.getNom(), "Boyd");
        Assertions.assertEquals(personne.getAdresse(), "1509 Culver St");
        Assertions.assertEquals(personne.getVille(), "Culver");
        Assertions.assertEquals(personne.getCodePostal(), "97451");
        Assertions.assertEquals(personne.getTelephone(), "841-874-6512");
        Assertions.assertEquals(personne.getEmail(), "jaboyd@email.com");

    }
}
