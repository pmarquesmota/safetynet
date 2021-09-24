package com.safetynet.safetynet.entity;

import com.safetynet.safetynet.model.Person;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
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

    @JoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    private DossierMedical dossierMedical;

    public Personne(Person person) {
        prenom = person.getFirstName();
        nom = person.getLastName();
        adresse = person.getAddress();
        ville = person.getCity();
        codePostal = person.getZip();
        telephone = person.getPhone();
        email = person.getEmail();
    }

}
