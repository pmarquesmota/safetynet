package com.safetynet.safetynet.service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BirthdayTest {
    @Test
    public void createCasernesTest() {
        SimpleDateFormat date = new SimpleDateFormat("01/01/2003");
        Assertions.assertEquals(new Date("01/01/2003"), Birthday.initBirthday());
    }

}
