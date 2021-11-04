package com.safetynet.safetynet.service;


import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BirthdayService {
    public Date initBirthday() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, -18);
        return c.getTime();
    }

    public long getAge(Date d) {
        return (System.currentTimeMillis()
                - d.getTime())
                / (1000l * 60 * 60 * 24 * 365);
    }


}
