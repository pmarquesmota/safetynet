package com.safetynet.safetynet.service;

import com.safetynet.safetynet.entity.CasernePompier;
import com.safetynet.safetynet.repository.CasernePompierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CasernePompierService {
    @Autowired
    CasernePompierRepository casernePompierRepository;

    public CasernePompier getCaserne(Long id) throws NoSuchElementException {
        return casernePompierRepository.findById(id).orElseThrow(() -> new NoSuchElementException("La caserne " + id + " n'existe pas"));
    }

    public List<CasernePompier> getCasernes() {
        return casernePompierRepository.findAll();
    }

    public CasernePompier addCaserne(CasernePompier casernePompier) {
        return casernePompierRepository.save(casernePompier);
    }

    public CasernePompier modifyCaserne(Long id, CasernePompier casernePompier) throws NoSuchElementException {
        CasernePompier oldCaserne = casernePompierRepository.findById(id).orElseThrow(() -> new NoSuchElementException("La caserne " + id + " n'existe pas"));
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
