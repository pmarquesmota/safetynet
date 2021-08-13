package com.safetynet.safetynet.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Personne {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String prenom;

    @Column
    private String nom;

    @Column
    private String adresse;

    @Column
    private String ville;

    @Column
    private String codePostal;

    @Column
    private String telephone;

    @Column
    private String email;

    @Column
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    @JoinColumn
    @OneToOne
    private DossierMedical dossierMedical;

}
