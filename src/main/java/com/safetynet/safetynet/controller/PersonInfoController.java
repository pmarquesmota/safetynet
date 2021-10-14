package com.safetynet.safetynet.controller;

import com.safetynet.safetynet.entity.Personne;
import com.safetynet.safetynet.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/personInfo")
public class PersonInfoController {
    @Autowired
    private PersonInfoService personInfoService;

    @GetMapping("")
    public ResponseEntity<List<Personne>> getPersonInfo(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        try {
            return ResponseEntity.ok(personInfoService.getPersonInfo(firstName, lastName));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
