package com.safetynet.safetynet.controller;

import com.safetynet.safetynet.model.Fire;
import com.safetynet.safetynet.service.FireService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/fire")
public class FireController {
    @Autowired
    private FireService fireService;

    @GetMapping("")
    public ResponseEntity<Fire> getFire(@RequestParam("address") String address) {
        log.info("GET /fire?address=" + address);
        try {
            return ResponseEntity.ok(fireService.getFire(address));
        } catch (NoSuchElementException e) {
            log.error("GET /fire ERROR : " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
