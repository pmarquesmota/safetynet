package com.safetynet.safetynet.controller;

import com.jsoniter.JsonIterator;
import com.safetynet.safetynet.model.Data;
import com.safetynet.safetynet.service.ReadFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SafetynetController {

    @GetMapping("/world")
    public Data hello() {
        String json = ReadFile.read();
        Data data = JsonIterator.deserialize(json, Data.class);
        return data;
    }

}
