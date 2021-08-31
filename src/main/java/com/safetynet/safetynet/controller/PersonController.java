package com.safetynet.safetynet.controller;

import com.safetynet.safetynet.entity.Personne;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {
    @GetMapping("")
    public String getPersons() {
        //utiliser la couche service
        return "Not implemented";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") Long id) {
        return "Not implemented";
    }

    @PostMapping("")
    public String addPerson(@RequestBody Personne personne) {
        return "Not implemented";
    }

    @PutMapping("/{id}")
    public String modifyPerson(@PathVariable("id") Long id, @RequestBody Personne personne) {
        return "Not implemented";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") Long id) {
        return "Not implemented";
    }

}
