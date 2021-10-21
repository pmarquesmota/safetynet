package com.safetynet.safetynet.controller;

import com.safetynet.safetynet.entity.CasernePompier;
import com.safetynet.safetynet.service.CasernePompierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/firestation")
public class FirestationController {
    @Autowired
    private CasernePompierService casernePompierService;

    @GetMapping("")
    public ResponseEntity<List<CasernePompier>> getFirestations() {
        log.info("GET /firestation");
        return ResponseEntity.ok(casernePompierService.getCasernes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CasernePompier> getFirestation(@PathVariable("id") Long id) {
        log.info("GET /firestation/" + id);
        try {
            return ResponseEntity.ok(casernePompierService.getCaserne(id));
        } catch (NoSuchElementException e) {
            log.error("GET /firestation ERROR : " + e.getMessage());
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("")
    public ResponseEntity<CasernePompier> addCaserne(@RequestBody CasernePompier caserne) {
        log.info("POST /firestation/" + " " + caserne.toString());
        return ResponseEntity.ok(casernePompierService.addCaserne(caserne));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CasernePompier> modifyCaserne(@PathVariable("id") Long id, @RequestBody CasernePompier caserne) {
        log.info("PUT /firestation/" + id + " " + caserne.toString());
        try {
            return ResponseEntity.ok(casernePompierService.modifyCaserne(id, caserne));
        } catch (NoSuchElementException e) {
            log.error("PUT /firestation ERROR : " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCaserne(@PathVariable("id") Long id) {
        log.info("DELETE /firestation/" + id);
        try {
            casernePompierService.deleteCaserne(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            log.error("DELETE /firestation ERROR : " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

}
