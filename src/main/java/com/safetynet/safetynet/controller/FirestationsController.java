package com.safetynet.safetynet.controller;

import com.safetynet.safetynet.model.StationNumber;
import com.safetynet.safetynet.service.CasernePompierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/firestations")
public class FirestationsController {
    @Autowired
    private CasernePompierService casernePompierService;

    @GetMapping("")
    public ResponseEntity<StationNumber> getStationNumber(@RequestParam("stationNumber") Long stationNumber) {
        log.info("GET /firestations?stationNumber=" + stationNumber);
        return ResponseEntity.ok(casernePompierService.getStationNumber(stationNumber));
    }

}
