package com.safetynet.safetynet;

import com.safetynet.safetynet.entity.DossierMedical;
import com.safetynet.safetynet.model.MedicalRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SafetynetApplicationTests {

    @Test
    void findDossierMedicalTest() {
        MedicalRecord medicalRecord = new MedicalRecord("John", "Doe", "03/06/1984", new String[0], new String[0]);
        MedicalRecord[] medicalRecords = new MedicalRecord[1];
        medicalRecords[0] = medicalRecord;

        DossierMedical dm = new DossierMedical(medicalRecord);

        DossierMedical dossierMedical = SafetynetApplication.findDossierMedical(medicalRecords, "John", "Doe");

        Assertions.assertEquals(dm, dossierMedical);

    }

    @Test
    void findDossierMedicalTestBadDate() {
        MedicalRecord medicalRecord = new MedicalRecord("John", "Doe", "aa/bb/cccc", new String[0], new String[0]);
        MedicalRecord[] medicalRecords = new MedicalRecord[1];
        medicalRecords[0] = medicalRecord;

        DossierMedical dossierMedical = SafetynetApplication.findDossierMedical(medicalRecords, "John", "Doe");

        Assertions.assertEquals(dossierMedical.getDateNaissance(), null);

    }
}
