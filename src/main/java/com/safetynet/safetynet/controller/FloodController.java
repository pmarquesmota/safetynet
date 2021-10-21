package com.safetynet.safetynet.controller;

import com.safetynet.safetynet.model.Flood;
import com.safetynet.safetynet.service.FloodService;
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
@RequestMapping("/flood/stations")
public class FloodController {
    @Autowired
    private FloodService floodService;

    @GetMapping("")
    public ResponseEntity<List<Flood>> getFlood(@RequestParam("stations") List<Long> stations) {
        log.info("GET /flood/stations?stations=" + stations);
        try {
            return ResponseEntity.ok(floodService.getFlood(stations));
        } catch (NoSuchElementException e) {
            log.error("GET /lood/stations ERROR : " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
