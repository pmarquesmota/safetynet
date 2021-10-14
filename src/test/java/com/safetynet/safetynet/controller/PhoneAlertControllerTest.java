package com.safetynet.safetynet.controller;

import com.safetynet.safetynet.model.PhoneAlert;
import com.safetynet.safetynet.service.PhoneAlertService;
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
public class PhoneAlertControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    PhoneAlertService phoneAlertService;

    @Test
    public void getPhoneAlert_shouldReturnOK() throws Exception {
        when(phoneAlertService.getPhoneAlert(any())).thenReturn(new PhoneAlert());

        mvc.perform(get("/phoneAlert?firestation=1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getPhoneAlert_shouldReturnNotFound() throws Exception {
        when(phoneAlertService.getPhoneAlert(any())).thenThrow(new NoSuchElementException());

        mvc.perform(get("/phoneAlert?firestation=1"))
                .andExpect(status().isNotFound());
    }

}
