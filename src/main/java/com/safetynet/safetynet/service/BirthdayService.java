package com.safetynet.safetynet.service;


import com.safetynet.safetynet.repository.BirthdayRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BirthdayService implements BirthdayRepository {

    // Get the date 18 years before
    public Date initBirthday() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, -18);
        return c.getTime();
    }

    // Return the age in years from the date
    public long getAge(Date d) {
        return (System.currentTimeMillis()
                - d.getTime())
                / (1000l * 60 * 60 * 24 * 365);
    }


}
