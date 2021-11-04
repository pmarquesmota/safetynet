package com.safetynet.safetynet;

import com.jsoniter.JsonIterator;
import com.safetynet.safetynet.entity.CasernePompier;
import com.safetynet.safetynet.entity.DossierMedical;
import com.safetynet.safetynet.entity.Personne;
import com.safetynet.safetynet.model.Data;
import com.safetynet.safetynet.model.MedicalRecord;
import com.safetynet.safetynet.repository.CasernePompierRepository;
import com.safetynet.safetynet.repository.PersonneRepository;
import com.safetynet.safetynet.repository.ReadDataFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@SpringBootApplication
public class SafetynetApplication implements CommandLineRunner {

    @Autowired
    PersonneRepository personneRepository;

    @Autowired
    CasernePompierRepository casernePompierRepository;

    @Autowired
    ReadDataFileRepository readDataFileRepository;

    public static void main(String[] args) {
        SpringApplication.run(SafetynetApplication.class, args);
    }

    // Return the DossierMedical object from the data.json medical record, the first name and last name
    DossierMedical findDossierMedical(MedicalRecord[] medicalRecord, String prenom, String nom) {

        List<MedicalRecord> m = Arrays.asList(medicalRecord)
                .stream()
                .filter(r -> (r.firstName.equals(prenom) && r.lastName.equals(nom)))
                .collect(Collectors.toList());

        DossierMedical dm = new DossierMedical(m.get(0));
        return dm;
    }

    // Create the List of Personne objects from the data.json, including medical records
    List<Personne> createPersonnes(Data data) {
        List<Personne> personnes = new ArrayList<>();

        Arrays.asList(data.persons).forEach(name -> {
            Personne tmpPersonne = new Personne(name);
            // trouver dans data.medicalrecords le medicalrecord qui correspond à la personne
            DossierMedical dossierMedical = findDossierMedical(data.medicalrecords, name.getFirstName(), name.getLastName());
            tmpPersonne.setDossierMedical(dossierMedical);
            personnes.add(tmpPersonne);
        });
        return personnes;
    }

    // Create the firestation from the data.json
    List<CasernePompier> createCasernes(Data data) {
        List<CasernePompier> casernes = new ArrayList<>();
        AtomicReference<Boolean> foundCaserne = new AtomicReference<>(false);

        Arrays.asList(data.firestations).forEach(name -> {
            casernes.forEach(casernePompier -> {
                // If the casernePompier object already exists, we add the address to the existing object
                if (casernePompier.getId() == Long.parseLong(name.station)) {
                    foundCaserne.set(true);

                    List<String> adresses = casernePompier.getAdresses();
                    adresses.add(name.address);
                    casernePompier.setAdresses(adresses);
                }
            });
            // If the casernePompier object doesn't exists, we add a new object
            if (!foundCaserne.get()) {
                casernes.add(new CasernePompier(name));
            }
            foundCaserne.set(false);
        });

        return casernes;
    }

    @Override
    public void run(String... args) {

        // extraire les données du json
        String json = readDataFileRepository.read();
        Data data = JsonIterator.deserialize(json, Data.class);

        //  convertir les 3 objets
        //      liste de personnes, dossiers médicaux, casernes
        List<Personne> personnes = createPersonnes(data);
        List<CasernePompier> casernes = createCasernes(data);

        //  enregistrer les données dans la db
        personneRepository.saveAll(personnes);
        casernePompierRepository.saveAll(casernes);
    }
}
