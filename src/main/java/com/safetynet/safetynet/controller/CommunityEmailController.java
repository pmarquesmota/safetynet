package com.safetynet.safetynet.controller;

import com.safetynet.safetynet.model.Email;
import com.safetynet.safetynet.service.CommunityEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/communityEmail")
public class CommunityEmailController {
    @Autowired
    private CommunityEmailService communityEmailService;

    @GetMapping("")
    public ResponseEntity<List<Email>> getChildAlert(@RequestParam("city") String city) {
        log.info("GET /communityEmail?city=" + city);
        try {
            return ResponseEntity.ok(communityEmailService.getCommunityEmailService(city));
        } catch (NoSuchElementException e) {
            log.error("GET /communityEmail ERROR : " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
