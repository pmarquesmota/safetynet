package com.safetynet.safetynet.controller;

import com.safetynet.safetynet.service.CommunityEmailService;
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
public class CommunityEmailControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    CommunityEmailService communityEmailService;

    @Test
    public void getCommunityEmailService_shouldReturnOK() throws Exception {
        when(communityEmailService.getCommunityEmailService(any())).thenReturn(new ArrayList<>());

        mvc.perform(get("/communityEmail?city=Paris"))
                .andExpect(status().isOk());
    }

    @Test
    public void getCommunityEmailService_shouldReturnNotFound() throws Exception {
        when(communityEmailService.getCommunityEmailService(any())).thenThrow(new NoSuchElementException());

        mvc.perform(get("/communityEmail?city=Paris"))
                .andExpect(status().isNotFound());
    }
}
