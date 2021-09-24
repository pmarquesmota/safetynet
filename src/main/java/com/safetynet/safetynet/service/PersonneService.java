package com.safetynet.safetynet.service;

import com.safetynet.safetynet.entity.Personne;
import com.safetynet.safetynet.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonneService {
    @Autowired
    PersonneRepository personneRepository;

    public Personne getPersonne(Long id) throws NoSuchElementException {
        //personneRepository.findByPrenom();
        return personneRepository.findById(id).orElseThrow(() -> new NoSuchElementException("La personne " + id + " n'existe pas"));
    }

    public List<Personne> getPersonnes() {
        return personneRepository.findAll();
    }

    public Personne addPersonne(Personne personne) {
        return personneRepository.save(personne);
    }

    public Personne modifyPersonne(Long id, Personne personne) throws NoSuchElementException {
        Personne oldPersonne = personneRepository.findById(id).orElseThrow(() -> new NoSuchElementException("La personne " + id + " n'existe pas"));
        oldPersonne.setPrenom(personne.getPrenom());
        oldPersonne.setNom(personne.getNom());
        oldPersonne.setAdresse(personne.getAdresse());
        oldPersonne.setVille(personne.getVille());
        oldPersonne.setCodePostal(personne.getCodePostal());
        oldPersonne.setTelephone(personne.getTelephone());
        oldPersonne.setEmail(personne.getEmail());
        oldPersonne.setDossierMedical(personne.getDossierMedical());
        return personneRepository.save(personne);
    }

    public void deletePersonne(Long id) throws NoSuchElementException {
        if (personneRepository.existsById(id)) {
            personneRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("La personne " + id + " n'existe pas");
        }
    }

}
