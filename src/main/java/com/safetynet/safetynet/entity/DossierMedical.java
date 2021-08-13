package com.safetynet.safetynet.entity;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
public class DossierMedical {
    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    private List<String> medicaments;

    @ElementCollection
    private List<String> allergies;

}
