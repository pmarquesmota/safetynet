package com.safetynet.safetynet.service;

import com.safetynet.safetynet.entity.DossierMedical;
import com.safetynet.safetynet.repository.DossierMedicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DossierMedicalService {
    @Autowired
    DossierMedicalRepository dossierMedicalRepository;

    public DossierMedical getDossierMedical(Long id) throws NoSuchElementException {
        return dossierMedicalRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Le dossier medical " + id + " n'existe pas"));
    }

    public List<DossierMedical> getDossierMedicaux() {
        return dossierMedicalRepository.findAll();
    }

    public DossierMedical addDossierMedical(DossierMedical dossierMedical) {
        return dossierMedicalRepository.save(dossierMedical);
    }

    public DossierMedical modifyDossierMedical(Long id, DossierMedical dossierMedical) throws NoSuchElementException {
        DossierMedical oldDossierMedical = dossierMedicalRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Le dossier medical " + id + " n'existe pas"));
        oldDossierMedical.setDateNaissance(dossierMedical.getDateNaissance());
        oldDossierMedical.setMedicaments(dossierMedical.getMedicaments());
        oldDossierMedical.setAllergies(dossierMedical.getAllergies());
        return dossierMedicalRepository.save(oldDossierMedical);
    }

    public void deleteDossierMedical(Long id) throws NoSuchElementException {
        if (dossierMedicalRepository.existsById(id)) {
            dossierMedicalRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Le dossier medical " + id + " n'existe pas");
        }
    }

}
