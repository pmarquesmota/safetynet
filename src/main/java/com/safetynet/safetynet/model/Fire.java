package com.safetynet.safetynet.model;

import com.safetynet.safetynet.entity.Personne;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Fire {
    List<Personne> persons;
    Long firestation;
}
