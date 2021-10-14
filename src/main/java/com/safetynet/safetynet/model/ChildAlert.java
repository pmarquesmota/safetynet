package com.safetynet.safetynet.model;

import com.safetynet.safetynet.entity.Personne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ChildAlert {
    List<Child> enfants;
    List<Personne> autres;
}
