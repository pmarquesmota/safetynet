package com.safetynet.safetynet.repository;

import com.safetynet.safetynet.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {
    List<Personne> findAllByAdresse(String address);

    List<Personne> findByPrenomAndNom(String prenom, String nom);

    List<Personne> findByVille(String city);
}
