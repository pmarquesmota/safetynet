package com.safetynet.safetynet.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Data {
    public Person[] persons;
    public FireStation[] firestations;
    public MedicalRecord[] medicalrecords;

}
