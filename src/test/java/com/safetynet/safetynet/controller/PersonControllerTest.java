package com.safetynet.safetynet.controller;

import com.safetynet.safetynet.entity.Personne;
import com.safetynet.safetynet.repository.PersonneRepository;
import com.safetynet.safetynet.service.PersonneService;
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
public class PersonControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    PersonneRepository personneRepository;

    @MockBean
    PersonneService personneService;

    @Test
    public void getPersons_shouldReturnOK() throws Exception {
        List<Personne> listePersonnes = new ArrayList<>();
        listePersonnes.add(new Personne());
        when(personneService.getPersonnes()).thenReturn(listePersonnes);

        mvc.perform(get("/person"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void getPerson_shouldReturnOK() throws Exception {
        when(personneService.getPersonne(any())).thenReturn(new Personne());

        mvc.perform(get("/person/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getPerson_shouldReturnNotFound() throws Exception {
        when(personneService.getPersonne(any())).thenThrow(new NoSuchElementException());

        mvc.perform(get("/person/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addPersons_shouldReturnOK() throws Exception {
        when(personneService.addPersonne(any())).thenReturn(new Personne());

        this.mvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"prenom\":\"Paul\",\"nom\":\"Marques Mota\",\"adresse\":\"1509 Culver St\",\"ville\":\"Culver\",\"codePostal\":\"97451\",\"telephone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\",\"dossierMedical\":{\"dateNaissance\":\"1984-03-06\",\"medicaments\":[\"aznol:350mg\",\"hydrapermazol:100mg\"],\"allergies\":[\"nillacilan\"]}}"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void modifyPersonne_shouldReturnOK() throws Exception {
        when(personneService.modifyPersonne(any(), any())).thenReturn(new Personne());

        mvc.perform(put("/person/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"prenom\":\"Paul\",\"nom\":\"Marques Mota\",\"adresse\":\"1509 Culver St\",\"ville\":\"Culver\",\"codePostal\":\"97451\",\"telephone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\",\"dossierMedical\":{\"dateNaissance\":\"1984-03-06\",\"medicaments\":[\"aznol:350mg\",\"hydrapermazol:100mg\"],\"allergies\":[\"nillacilan\"]}}"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void modifyPersonne_shouldReturnNotFound() throws Exception {
        when(personneService.modifyPersonne(any(), any())).thenThrow(new NoSuchElementException());

        mvc.perform(put("/person/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"prenom\":\"Paul\",\"nom\":\"Marques Mota\",\"adresse\":\"1509 Culver St\",\"ville\":\"Culver\",\"codePostal\":\"97451\",\"telephone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\",\"dossierMedical\":{\"dateNaissance\":\"1984-03-06\",\"medicaments\":[\"aznol:350mg\",\"hydrapermazol:100mg\"],\"allergies\":[\"nillacilan\"]}}"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void deletePersonne_shouldReturnOK() throws Exception {
        PersonneService personneService = mock(PersonneService.class);
        doNothing().when(personneService).deletePersonne(anyLong());

        mvc.perform(delete("/person/1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void deletePersonne_shouldReturnNotFound() throws Exception {
        doThrow(new NoSuchElementException()).when(personneService).deletePersonne(anyLong());

        mvc.perform(delete("/person/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
