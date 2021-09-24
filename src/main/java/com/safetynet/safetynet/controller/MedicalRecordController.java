package com.safetynet.safetynet.controller;

import com.safetynet.safetynet.entity.DossierMedical;
import com.safetynet.safetynet.service.DossierMedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {
    @Autowired
    private DossierMedicalService dossierMedicalService;

    @GetMapping("")
    public ResponseEntity<List<DossierMedical>> getMedicalRecord() {
        return ResponseEntity.ok(dossierMedicalService.getDossierMedicaux());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DossierMedical> getDossierMedical(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(dossierMedicalService.getDossierMedical(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("")
    public ResponseEntity<DossierMedical> addDossierMedical(@RequestBody DossierMedical dossierMedical) {
        return ResponseEntity.ok(dossierMedicalService.addDossierMedical(dossierMedical));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DossierMedical> modifyDossierMedical(@PathVariable("id") Long id, @RequestBody DossierMedical dossierMedical) {
        try {
            return ResponseEntity.ok(dossierMedicalService.modifyDossierMedical(id, dossierMedical));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDossierMedical(@PathVariable("id") Long id) {
        try {
            dossierMedicalService.deleteDossierMedical(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
