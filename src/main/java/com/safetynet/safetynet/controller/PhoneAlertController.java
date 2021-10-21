package com.safetynet.safetynet.controller;

import com.safetynet.safetynet.model.PhoneAlert;
import com.safetynet.safetynet.service.PhoneAlertService;
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
@RequestMapping("/phoneAlert")
public class PhoneAlertController {
    @Autowired
    private PhoneAlertService phoneAlertService;

    @GetMapping("")
    public ResponseEntity<PhoneAlert> getPhoneAlert(@RequestParam("firestation") Long firestation) {
        log.info("GET /phoneAlert");
        try {
            return ResponseEntity.ok(phoneAlertService.getPhoneAlert(firestation));
        } catch (NoSuchElementException e) {
            log.error("GET /phoneAlert ERROR : " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
