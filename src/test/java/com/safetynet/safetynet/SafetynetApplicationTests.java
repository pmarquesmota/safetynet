package com.safetynet.safetynet;

import com.safetynet.safetynet.entity.CasernePompier;
import com.safetynet.safetynet.entity.DossierMedical;
import com.safetynet.safetynet.entity.Personne;
import com.safetynet.safetynet.model.Data;
import com.safetynet.safetynet.model.FireStation;
import com.safetynet.safetynet.model.MedicalRecord;
import com.safetynet.safetynet.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class SafetynetApplicationTests {

    @Test
    void createCasernesTest() {
        FireStation fireStation = new FireStation("1509 Culver St", "3");
        FireStation[] fireStations = new FireStation[1];
        fireStations[0] = fireStation;
        Data data = new Data(new Person[]{}, fireStations, new MedicalRecord[]{});

        SafetynetApplication safetynetApplication = new SafetynetApplication();
        List<CasernePompier> casernePompiers = safetynetApplication.createCasernes(data);

        Assertions.assertEquals("1509 Culver St", casernePompiers.get(0).getAdresses().get(0));
    }

    @Test
    void createCasernesDuplicateTest() {
        FireStation fireStation1 = new FireStation("1509 Culver St", "3");
        FireStation fireStation2 = new FireStation("834 Binoc Ave", "3");
        FireStation[] fireStations = new FireStation[2];
        fireStations[0] = fireStation1;
        fireStations[1] = fireStation2;
        Data data = new Data(new Person[]{}, fireStations, new MedicalRecord[]{});

        SafetynetApplication safetynetApplication = new SafetynetApplication();
        List<CasernePompier> casernePompiers = safetynetApplication.createCasernes(data);

        Assertions.assertEquals("1509 Culver St", casernePompiers.get(0).getAdresses().get(0));
        Assertions.assertEquals("834 Binoc Ave", casernePompiers.get(0).getAdresses().get(1));
    }

    @Test
    void createPersonnesTest() {
        Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
        Person[] persons = new Person[1];
        persons[0] = person;

        MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd", "03/06/1984", new String[]{"aznol:350mg", "hydrapermazol:100mg"}, new String[]{"nillacilan"});
        MedicalRecord[] medicalRecords = new MedicalRecord[1];
        medicalRecords[0] = medicalRecord;

        Data data = new Data(persons, new FireStation[]{}, medicalRecords);

        SafetynetApplication safetynetApplication = new SafetynetApplication();
        List<Personne> personnes = safetynetApplication.createPersonnes(data);

        Assertions.assertEquals("John", personnes.get(0).getPrenom());
    }

    @Test
    void findDossierMedicalTest() {
        MedicalRecord medicalRecord = new MedicalRecord("John", "Doe", "03/06/1984", new String[0], new String[0]);
        MedicalRecord[] medicalRecords = new MedicalRecord[1];
        medicalRecords[0] = medicalRecord;

        DossierMedical dm = new DossierMedical(medicalRecord);

        SafetynetApplication safetynetApplication = new SafetynetApplication();
        DossierMedical dossierMedical = safetynetApplication.findDossierMedical(medicalRecords, "John", "Doe");

        Assertions.assertEquals(dm, dossierMedical);

    }

    @Test
    void findDossierMedicalTestBadDate() {
        MedicalRecord medicalRecord = new MedicalRecord("John", "Doe", "aa/bb/cccc", new String[0], new String[0]);
        MedicalRecord[] medicalRecords = new MedicalRecord[1];
        medicalRecords[0] = medicalRecord;

        SafetynetApplication safetynetApplication = new SafetynetApplication();
        DossierMedical dossierMedical = safetynetApplication.findDossierMedical(medicalRecords, "John", "Doe");

        //assertThrows(ParseException.class, () -> dossierMedical.getDateNaissance());
        Assertions.assertEquals(dossierMedical.getDateNaissance(), null);

    }
}
