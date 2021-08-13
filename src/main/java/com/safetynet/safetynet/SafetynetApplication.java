package com.safetynet.safetynet;

import com.safetynet.safetynet.entity.Personne;
import com.safetynet.safetynet.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SafetynetApplication implements CommandLineRunner {

    @Autowired
    PersonneRepository personneRepository;

    public static void main(String[] args) {
        SpringApplication.run(SafetynetApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Personne personne = new Personne();
        personne.setNom("toto");
        personne.setPrenom("toto");

        personneRepository.save(personne);
        // TODO extraire les données du json
        // TODO convertir les 3 objets
        //      liste de personnes, dossiers médicaux, casernes
        // TODO enregistrer les données dans la db
    }
}
