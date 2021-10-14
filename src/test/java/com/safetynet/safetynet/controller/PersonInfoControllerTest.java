package com.safetynet.safetynet.controller;

import com.safetynet.safetynet.service.PersonInfoService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@ExtendWith(MockitoExtension.class)
public class PersonInfoControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    PersonInfoService personInfoService;

    @Test
    public void getPersonInfo_shouldReturnOK() throws Exception {
        when(personInfoService.getPersonInfo(any(), any())).thenReturn(new ArrayList<>());

        mvc.perform(get("/personInfo?firstName=Paul&LastName=Marques%20Mota"))
                .andExpect(status().isOk());
    }

    @Test
    public void getPersonInfo_shouldReturnNotFound() throws Exception {
        when(personInfoService.getPersonInfo(any(), any())).thenThrow(new NoSuchElementException());

        mvc.perform(get("/personInfo?firstName=Paul&LastName=Marques%20Mota"))
                .andExpect(status().isNotFound());
    }

}
