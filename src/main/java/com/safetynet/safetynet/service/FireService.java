package com.safetynet.safetynet.service;

import com.safetynet.safetynet.entity.CasernePompier;
import com.safetynet.safetynet.entity.Personne;
import com.safetynet.safetynet.model.Fire;
import com.safetynet.safetynet.repository.CasernePompierRepository;
import com.safetynet.safetynet.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FireService {
    @Autowired
    CasernePompierRepository casernePompierRepository;

    @Autowired
    PersonneRepository personneRepository;

    public Fire getFire(String address) {

        List<Personne> personnes = personneRepository
                .findAllByAdresse(address);

        List<CasernePompier> casernePompier = casernePompierRepository
                .findByAdresses(address);
        return new Fire(personnes, casernePompier.get(0).getId());
    }
}
