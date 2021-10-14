package com.safetynet.safetynet.controller;

import com.safetynet.safetynet.model.Fire;
import com.safetynet.safetynet.service.FireService;
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
public class FireControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    FireService fireService;

    @Test
    public void getFire_shouldReturnOK() throws Exception {
        when(fireService.getFire(any())).thenReturn(new Fire());

        mvc.perform(get("/fire?address=Paris"))
                .andExpect(status().isOk());
    }

    @Test
    public void getFire_shouldReturnNotFound() throws Exception {
        when(fireService.getFire(any())).thenThrow(new NoSuchElementException());

        mvc.perform(get("/fire?address=Paris"))
                .andExpect(status().isNotFound());
    }

}
