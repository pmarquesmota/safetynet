package com.safetynet.safetynet.controller;

import com.safetynet.safetynet.entity.CasernePompier;
import com.safetynet.safetynet.repository.CasernePompierRepository;
import com.safetynet.safetynet.service.CasernePompierService;
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
public class FireStationControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    CasernePompierRepository casernePompierRepository;

    @MockBean
    CasernePompierService casernePompierService;

    @Test
    public void getCasernes_shouldReturnOK() throws Exception {
        List<CasernePompier> listeCasernePompier = new ArrayList<>();
        listeCasernePompier.add(new CasernePompier());
        when(casernePompierService.getCasernes()).thenReturn(listeCasernePompier);

        mvc.perform(get("/firestation"))
                .andExpect(status().isOk());
    }

    @Test
    public void getCaserne_shouldReturnOK() throws Exception {
        when(casernePompierService.getCaserne(any())).thenReturn(new CasernePompier());

        mvc.perform(get("/firestation/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getCaserne_shouldReturnNotFound() throws Exception {
        when(casernePompierService.getCaserne(any())).thenThrow(new NoSuchElementException());

        mvc.perform(get("/firestation/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addCaserne_shouldReturnOK() throws Exception {
        when(casernePompierService.addCaserne(any())).thenReturn(new CasernePompier());

        this.mvc.perform(post("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":69,\"adresses\":[\"644 rue de la Paix\",\"908 73rd St\",\"947 E. Rose Dr\"]}"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void modifyCaserne_shouldReturnOK() throws Exception {
        when(casernePompierService.modifyCaserne(any(), any())).thenReturn(new CasernePompier());

        mvc.perform(put("/firestation/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":69,\"adresses\":[\"644 rue de la Paix\",\"908 73rd St\",\"947 E. Rose Dr\"]}"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void modifyCaserne_shouldReturnNotFound() throws Exception {
        when(casernePompierService.modifyCaserne(any(), any())).thenThrow(new NoSuchElementException());

        mvc.perform(put("/firestation/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":69,\"adresses\":[\"644 rue de la Paix\",\"908 73rd St\",\"947 E. Rose Dr\"]}"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteCaserne_shouldReturnOK() throws Exception {
        CasernePompierService casernePompierService = mock(CasernePompierService.class);
        doNothing().when(casernePompierService).deleteCaserne(anyLong());

        mvc.perform(delete("/firestation/1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void deleteCaserne_shouldReturnNotFound() throws Exception {
        doThrow(new NoSuchElementException()).when(casernePompierService).deleteCaserne(anyLong());

        mvc.perform(delete("/firestation/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
