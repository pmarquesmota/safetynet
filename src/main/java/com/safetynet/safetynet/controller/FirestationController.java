package com.safetynet.safetynet.controller;

import com.safetynet.safetynet.entity.CasernePompier;
import com.safetynet.safetynet.service.CasernePompierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/firestation")
public class FirestationController {
    @Autowired
    private CasernePompierService casernePompierService;

    @GetMapping("")
    public ResponseEntity<List<CasernePompier>> getFirestations() {
        return ResponseEntity.ok(casernePompierService.getCasernes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CasernePompier> getFirestation(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(casernePompierService.getCaserne(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("")
    public ResponseEntity<CasernePompier> addCaserne(@RequestBody CasernePompier caserne) {
        return ResponseEntity.ok(casernePompierService.addCaserne(caserne));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CasernePompier> modifyCaserne(@PathVariable("id") Long id, @RequestBody CasernePompier caserne) {
        try {
            return ResponseEntity.ok(casernePompierService.modifyCaserne(id, caserne));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCaserne(@PathVariable("id") Long id) {
        try {
            casernePompierService.deleteCaserne(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
