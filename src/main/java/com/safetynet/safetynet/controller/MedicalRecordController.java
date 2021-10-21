package com.safetynet.safetynet.controller;

import com.safetynet.safetynet.entity.DossierMedical;
import com.safetynet.safetynet.service.DossierMedicalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {
    @Autowired
    private DossierMedicalService dossierMedicalService;

    @GetMapping("")
    public ResponseEntity<List<DossierMedical>> getMedicalRecord() {
        log.info("GET /medicalRecord");
        return ResponseEntity.ok(dossierMedicalService.getDossierMedicaux());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DossierMedical> getDossierMedical(@PathVariable("id") Long id) {
        log.info("GET /medicalRecord/" + id);
        try {
            return ResponseEntity.ok(dossierMedicalService.getDossierMedical(id));
        } catch (NoSuchElementException e) {
            log.error("GET /medicalRecord/" + id + " - Error : " + e.getMessage());
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("")
    public ResponseEntity<DossierMedical> addDossierMedical(@RequestBody DossierMedical dossierMedical) {
        log.info("POST /medicalRecord/" + " " + dossierMedical.toString());
        return ResponseEntity.ok(dossierMedicalService.addDossierMedical(dossierMedical));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DossierMedical> modifyDossierMedical(@PathVariable("id") Long id, @RequestBody DossierMedical dossierMedical) {
        log.info("PUT /medicalRecord/" + id + " " + dossierMedical.toString());
        try {
            return ResponseEntity.ok(dossierMedicalService.modifyDossierMedical(id, dossierMedical));
        } catch (NoSuchElementException e) {
            log.error("PUT /medicalRecord/" + id + " - Error : " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDossierMedical(@PathVariable("id") Long id) {
        log.info("DELETE /medicalRecord/" + id);
        try {
            dossierMedicalService.deleteDossierMedical(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            log.error("DELETE /medicalRecord/" + id + " - Error : " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

}
