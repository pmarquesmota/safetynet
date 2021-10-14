package com.safetynet.safetynet.controller;

import com.safetynet.safetynet.model.Fire;
import com.safetynet.safetynet.service.FireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/fire")
public class FireController {
    @Autowired
    private FireService fireService;

    @GetMapping("")
    public ResponseEntity<Fire> getFire(@RequestParam("address") String address) {
        try {
            return ResponseEntity.ok(fireService.getFire(address));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
