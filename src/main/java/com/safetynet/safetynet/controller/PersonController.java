package com.safetynet.safetynet.controller;

import com.safetynet.safetynet.entity.Personne;
import com.safetynet.safetynet.service.PersonneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonneService personneService;

    @GetMapping("")
    public ResponseEntity<List<Personne>> getPersons() {
        return ResponseEntity.ok(personneService.getPersonnes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personne> getPerson(@PathVariable("id") Long id) {
        log.info("GET /person/" + id);
        try {
            return ResponseEntity.ok(personneService.getPersonne(id));
        } catch (NoSuchElementException e) {
            log.error("GET /person/" + id + " - Error : " + e.getMessage());
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("")
    public ResponseEntity<Personne> addPerson(@RequestBody Personne personne) {
        return ResponseEntity.ok(personneService.addPersonne(personne));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personne> modifyPerson(@PathVariable("id") Long id, @RequestBody Personne personne) {
        try {
            return ResponseEntity.ok(personneService.modifyPersonne(id, personne));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable("id") Long id) {
        try {
            personneService.deletePersonne(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
