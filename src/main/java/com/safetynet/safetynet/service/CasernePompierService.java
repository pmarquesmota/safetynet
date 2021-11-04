package com.safetynet.safetynet.service;

import com.safetynet.safetynet.entity.CasernePompier;
import com.safetynet.safetynet.entity.Personne;
import com.safetynet.safetynet.model.StationNumber;
import com.safetynet.safetynet.repository.BirthdayRepository;
import com.safetynet.safetynet.repository.CasernePompierRepository;
import com.safetynet.safetynet.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CasernePompierService {
    @Autowired
    private CasernePompierRepository casernePompierRepository;

    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private BirthdayRepository birthdayRepository;

    public CasernePompier getCaserne(Long id) throws NoSuchElementException {
        return casernePompierRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("La caserne " + id + " n'existe pas"));
    }

    public StationNumber getStationNumber(Long id) throws NoSuchElementException {
        Date birthday = birthdayRepository.initBirthday();

        CasernePompier casernePompier = casernePompierRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("La caserne " + id + " n'existe pas"));

        List<Personne> personnes = initPersonnes(casernePompier);

        long adultes = initAdultes(casernePompier, birthday);

        long enfants = initEnfants(casernePompier, birthday);

        return new StationNumber(personnes, adultes, enfants);
    }

    // Returns the persons living at the casern
    List<Personne> initPersonnes(CasernePompier casernePompier) {
        List<Personne> personnes = new ArrayList<>();
        casernePompier
                .getAdresses()
                .forEach(adresse ->
                        personnes.addAll(personneRepository.findAllByAdresse(adresse)));
        return personnes;
    }

    // Count the adults living at the casern
    long initAdultes(CasernePompier casernePompier, Date birthday) {
        return casernePompier
                .getAdresses()
                .stream()
                .map(adresse ->
                        personneRepository.
                                findAllByAdresse(adresse))
                .flatMap(Collection::stream)
                .filter(p -> birthdayRepository.getAge(p.getDossierMedical().getDateNaissance()) >= 18)
                .count();
    }

    // Count the children living at the casern
    long initEnfants(CasernePompier casernePompier, Date birthday) {
        return casernePompier
                .getAdresses()
                .stream()
                .map(adresse ->
                        personneRepository.
                                findAllByAdresse(adresse))
                .flatMap(Collection::stream)
                .filter(p -> birthdayRepository.getAge(p.getDossierMedical().getDateNaissance()) < 18)
                .count();
    }

    public List<CasernePompier> getCasernes() {
        return casernePompierRepository.findAll();
    }

    public CasernePompier addCaserne(CasernePompier casernePompier) {
        return casernePompierRepository.save(casernePompier);
    }

    public CasernePompier modifyCaserne(Long id, CasernePompier casernePompier) throws NoSuchElementException {
        CasernePompier oldCaserne = casernePompierRepository
                .findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("La caserne " + id + " n'existe pas"));
        oldCaserne.setAdresses(casernePompier.getAdresses());
        return casernePompierRepository.save(oldCaserne);
    }

    public void deleteCaserne(Long id) throws NoSuchElementException {
        if (casernePompierRepository.existsById(id)) {
            casernePompierRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("La caserne " + id + " n'existe pas");
        }
    }

}
