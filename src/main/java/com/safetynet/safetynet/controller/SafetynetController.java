package com.safetynet.safetynet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SafetynetController {

    @GetMapping("/world")
    public Toto hello() {
        SafetynetController.Toto x = new SafetynetController.Toto();
        x.a = "titi";
        x.b = "tutu";
        return x;
    }
    
    public class Toto {
        public String a;
        public String b;
    }
}
