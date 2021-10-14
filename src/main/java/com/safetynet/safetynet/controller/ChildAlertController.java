package com.safetynet.safetynet.controller;

import com.safetynet.safetynet.model.ChildAlert;
import com.safetynet.safetynet.service.ChildAlertService;
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
@RequestMapping("/childAlert")
public class ChildAlertController {
    @Autowired
    private ChildAlertService childAlertService;

    @GetMapping("")
    public ResponseEntity<ChildAlert> getChildAlert(@RequestParam("address") String address) {
        log.info("GET /childAlert?address=" + address);
        try {
            return ResponseEntity.ok(childAlertService.getChildAlert(address));
        } catch (NoSuchElementException e) {
            log.error("GET /childAlert ERROR : " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}