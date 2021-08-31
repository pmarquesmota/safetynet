package com.safetynet.safetynet.entity;

import com.safetynet.safetynet.model.MedicalRecord;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@RequiredArgsConstructor
public class DossierMedical {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    @ElementCollection
    private List<String> medicaments;

    @ElementCollection
    private List<String> allergies;

    public DossierMedical(MedicalRecord record) {
        try {
            dateNaissance = new SimpleDateFormat("MM/dd/yyyy").parse(record.birthdate);
        } catch (ParseException e) {
            dateNaissance = null;
            e.printStackTrace();
        }

        medicaments = new ArrayList<String>();
        medicaments.addAll(Arrays.asList(record.medications));

        allergies = new ArrayList<String>();
        allergies.addAll(Arrays.asList(record.allergies));

    }

}
