package com.safetynet.safetynet.repository;

import com.safetynet.safetynet.entity.CasernePompier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CasernePompierRepository extends JpaRepository<CasernePompier, Long> {

    List<CasernePompier> findByAdresses(String address);
}