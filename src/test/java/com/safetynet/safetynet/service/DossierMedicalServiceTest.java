package com.safetynet.safetynet.service;

import com.safetynet.safetynet.entity.DossierMedical;
import com.safetynet.safetynet.model.MedicalRecord;
import com.safetynet.safetynet.repository.DossierMedicalRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DossierMedicalServiceTest {
    @Mock
    DossierMedicalRepository dossierMedicalRepository;

    @InjectMocks
    DossierMedicalService dossierMedicalService;

    @Test
    public void getDossierMedical_shouldReturnOk() {
        DossierMedical dossierMedical = new DossierMedical(new MedicalRecord(
                "John",
                "Boyd",
                "03/06/1984",
                new String[]{"aznol:350mg", "hydrapermazol:100mg"},
                new String[]{"nillacilan"}
        ));
        when(dossierMedicalRepository.findById(any())).thenReturn(java.util.Optional.of(dossierMedical));

        assertEquals(dossierMedical, dossierMedicalService.getDossierMedical(1L));

    }

    @Test
    public void getDossierMedical_shouldReturnNotFound() {
        when(dossierMedicalRepository.findById(any())).thenThrow(new NoSuchElementException());
        assertThrows(NoSuchElementException.class, () -> dossierMedicalService.getDossierMedical(1L));

    }

    @Test
    public void getDossierMedicaux_shouldReturnOk() {
        DossierMedical dossierMedical = new DossierMedical(new MedicalRecord(
                "John",
                "Boyd",
                "03/06/1984",
                new String[]{"aznol:350mg", "hydrapermazol:100mg"},
                new String[]{"nillacilan"}
        ));
        List<DossierMedical> dossierMedicaux = List.of(dossierMedical);
        when(dossierMedicalRepository.findAll()).thenReturn(dossierMedicaux);

        assertEquals(dossierMedicaux, dossierMedicalService.getDossierMedicaux());

    }

    @Test
    public void addDossierMedical_shouldReturnOk() {
        DossierMedical dossierMedical = new DossierMedical(new MedicalRecord(
                "John",
                "Boyd",
                "03/06/1984",
                new String[]{"aznol:350mg", "hydrapermazol:100mg"},
                new String[]{"nillacilan"}
        ));
        when(dossierMedicalRepository.save(any())).thenReturn(dossierMedical);

        assertEquals(dossierMedical, dossierMedicalService.addDossierMedical(dossierMedical));

    }

    @Test
    public void modifyDossierMedical_shouldReturnOk() {
        DossierMedical oldDossierMedical = new DossierMedical(new MedicalRecord(
                "John",
                "Boyd",
                "03/06/1984",
                new String[]{"pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"},
                new String[]{"nillacilan"}
        ));
        DossierMedical newDossierMedical = new DossierMedical(new MedicalRecord(
                "John",
                "Boyd",
                "03/06/1989",
                new String[]{"aznol:350mg", "hydrapermazol:100mg"},
                new String[]{}
        ));
        when(dossierMedicalRepository.findById(any())).thenReturn(java.util.Optional.of(oldDossierMedical));
        when(dossierMedicalRepository.save(any())).thenReturn(newDossierMedical);

        assertEquals(newDossierMedical, dossierMedicalService.modifyDossierMedical(1L, oldDossierMedical));

    }

    @Test
    public void modifyDossierMedical_shouldReturnNotFound() {
        when(dossierMedicalRepository.findById(any())).thenThrow(new NoSuchElementException());
        assertThrows(NoSuchElementException.class, () -> dossierMedicalService.modifyDossierMedical(1L, null));

    }

    @Test
    public void deleteDossierMedical_shouldReturnOk() {
        when(dossierMedicalRepository.existsById(any())).thenReturn(true);

        dossierMedicalService.deleteDossierMedical(1L);
        verify(dossierMedicalRepository).deleteById(any());
    }

}
