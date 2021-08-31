package com.safetynet.safetynet.entity;

import com.safetynet.safetynet.model.FireStation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class CasernePompier {
    @Id
    private Long id;

    @ElementCollection
    private List<String> adresses;

    public CasernePompier(FireStation fireStation) {
        id = Long.parseLong(fireStation.station);
        adresses = new ArrayList<>();
        adresses.add(fireStation.address);
    }
}
