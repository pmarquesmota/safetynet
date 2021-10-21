package com.safetynet.safetynet.model;

import com.safetynet.safetynet.entity.Personne;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class ChildAlert {
    List<Child> enfants;
    List<Personne> autres;
}
