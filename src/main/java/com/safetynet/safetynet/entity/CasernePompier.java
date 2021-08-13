package com.safetynet.safetynet.entity;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
public class CasernePompier {
    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    private List<String> adresses;
}
