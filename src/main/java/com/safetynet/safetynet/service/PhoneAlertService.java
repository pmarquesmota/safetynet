package com.safetynet.safetynet.service;

import com.safetynet.safetynet.entity.CasernePompier;
import com.safetynet.safetynet.entity.Personne;
import com.safetynet.safetynet.model.PhoneAlert;
import com.safetynet.safetynet.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneAlertService {
    @Autowired
    PersonneRepository personneRepository;

    @Autowired
    CasernePompierService casernePompierService;

    public PhoneAlert getPhoneAlert(Long id) {
        CasernePompier casernePompier = casernePompierService.getCaserne(id);

        List<Personne> personnes = casernePompierService.initPersonnes(casernePompier);

        List<String> telephones = personnes
                .stream()
                .map(personne -> personne.getTelephone())
                .collect(Collectors.toList());

        return new PhoneAlert(telephones);
    }
}
