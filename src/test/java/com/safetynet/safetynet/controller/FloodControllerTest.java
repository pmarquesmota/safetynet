package com.safetynet.safetynet.controller;

import com.safetynet.safetynet.model.Flood;
import com.safetynet.safetynet.service.FloodService;
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
import java.util.List;
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
public class FloodControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    FloodService floodService;

    @Test
    public void getFlood_shouldReturnOK() throws Exception {
        List<Flood> floods = new ArrayList<>();
        when(floodService.getFlood(any())).thenReturn(floods);

        mvc.perform(get("/flood/stations?stations=1,2"))
                .andExpect(status().isOk());
    }

    @Test
    public void getFlood_shouldReturnNotFound() throws Exception {
        when(floodService.getFlood(any())).thenThrow(new NoSuchElementException());

        mvc.perform(get("/flood/stations?stations=1,2"))
                .andExpect(status().isNotFound());
    }

}
