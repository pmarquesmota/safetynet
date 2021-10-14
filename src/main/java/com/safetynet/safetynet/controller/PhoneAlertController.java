package com.safetynet.safetynet.controller;

import com.safetynet.safetynet.model.PhoneAlert;
import com.safetynet.safetynet.service.PhoneAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/phoneAlert")
public class PhoneAlertController {
    @Autowired
    private PhoneAlertService phoneAlertService;

    @GetMapping("")
    public ResponseEntity<PhoneAlert> getPhoneAlert(@RequestParam("firestation") Long firestation) {
        try {
            return ResponseEntity.ok(phoneAlertService.getPhoneAlert(firestation));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
