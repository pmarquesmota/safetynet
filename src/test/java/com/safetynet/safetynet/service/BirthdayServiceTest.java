package com.safetynet.safetynet.service;

import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BirthdayServiceTest {

    @Test
    public void initBirthdayTest() {
        LocalDate oldDate = new BirthdayService().initBirthday()
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate newDate = LocalDate.now();
        long diff = ChronoUnit.YEARS.between(oldDate, newDate);
        assertEquals(18, diff);
    }

}
