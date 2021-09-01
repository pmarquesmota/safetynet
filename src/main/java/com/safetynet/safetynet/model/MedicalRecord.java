package com.safetynet.safetynet.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecord {
    public String firstName;
    public String lastName;
    public String birthdate;
    public String[] medications;
    public String[] allergies;

}
