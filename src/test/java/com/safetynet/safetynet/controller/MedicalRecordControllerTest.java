package com.safetynet.safetynet.controller;

import com.safetynet.safetynet.entity.DossierMedical;
import com.safetynet.safetynet.repository.DossierMedicalRepository;
import com.safetynet.safetynet.service.DossierMedicalService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@ExtendWith(MockitoExtension.class)
public class MedicalRecordControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    DossierMedicalRepository dossierMedicalRepository;

    @MockBean
    DossierMedicalService dossierMedicalService;

    @Test
    public void getDossierMedicaux_shouldReturnOK() throws Exception {
        List<DossierMedical> listeDossierMedical = new ArrayList<>();
        listeDossierMedical.add(new DossierMedical());
        when(dossierMedicalService.getDossierMedicaux()).thenReturn(listeDossierMedical);

        mvc.perform(get("/medicalRecord"))
                .andExpect(status().isOk());
    }

    @Test
    public void getDossierMedical_shouldReturnOK() throws Exception {
        when(dossierMedicalService.getDossierMedical(any())).thenReturn(new DossierMedical());

        mvc.perform(get("/medicalRecord/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getDossierMedical_shouldReturnNotFound() throws Exception {
        when(dossierMedicalService.getDossierMedical(any())).thenThrow(new NoSuchElementException());

        mvc.perform(get("/medicalRecord/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addDossierMedical_shouldReturnOK() throws Exception {
        when(dossierMedicalService.addDossierMedical(any())).thenReturn(new DossierMedical());

        this.mvc.perform(post("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"dateNaissance\":\"1984-03-06\",\"medicaments\":[\"aznol:350mg\",\"hydrapermazol:100mg\"],\"allergies\":[\"nillacilan\"]}"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void modifyDossierMedical_shouldReturnOK() throws Exception {
        when(dossierMedicalService.modifyDossierMedical(any(), any())).thenReturn(new DossierMedical());

        mvc.perform(put("/medicalRecord/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"dateNaissance\":\"1984-03-06\",\"medicaments\":[\"aznol:350mg\",\"hydrapermazol:100mg\"],\"allergies\":[\"nillacilan\"]}"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void modifyDossierMedical_shouldReturnNotFound() throws Exception {
        when(dossierMedicalService.modifyDossierMedical(any(), any())).thenThrow(new NoSuchElementException());

        mvc.perform(put("/medicalRecord/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"dateNaissance\":\"1984-03-06\",\"medicaments\":[\"aznol:350mg\",\"hydrapermazol:100mg\"],\"allergies\":[\"nillacilan\"]}"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void deletePersonne_shouldReturnOK() throws Exception {
        DossierMedicalService dossierMedicalService = mock(DossierMedicalService.class);
        doNothing().when(dossierMedicalService).deleteDossierMedical(anyLong());

        mvc.perform(delete("/medicalRecord/1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void deletePersonne_shouldReturnNotFound() throws Exception {
        doThrow(new NoSuchElementException()).when(dossierMedicalService).deleteDossierMedical(anyLong());

        mvc.perform(delete("/medicalRecord/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
