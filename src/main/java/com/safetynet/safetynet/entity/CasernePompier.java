package com.safetynet.safetynet.entity;

import com.safetynet.safetynet.model.FireStation;
import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
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
