package com.safetynet.safetynet.entity;

import com.safetynet.safetynet.model.FireStation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CasernePompierTests {

    @Test
    void CasernePompierTest() {
        FireStation fireStation = new FireStation("1509 Culver St", "3");
        CasernePompier casernePompier = new CasernePompier(fireStation);

        Assertions.assertEquals(casernePompier.getAdresses().get(0), "1509 Culver St");
        Assertions.assertEquals((long) casernePompier.getId(), 3);

    }
}
