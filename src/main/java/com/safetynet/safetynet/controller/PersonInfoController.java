package com.safetynet.safetynet.controller;

import com.safetynet.safetynet.entity.Personne;
import com.safetynet.safetynet.service.PersonInfoService;
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
@RequestMapping("/personInfo")
public class PersonInfoController {
    @Autowired
    private PersonInfoService personInfoService;

    @GetMapping("")
    public ResponseEntity<List<Personne>> getPersonInfo(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        log.info("GET /personInfo?firstName=" + firstName + "@lastName=" + lastName);
        try {
            return ResponseEntity.ok(personInfoService.getPersonInfo(firstName, lastName));
        } catch (NoSuchElementException e) {
            log.error("GET /personInfo ERROR : " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
