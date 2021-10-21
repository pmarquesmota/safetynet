package com.safetynet.safetynet.repository;

import java.util.Date;

public interface BirthdayRepository {
    Date initBirthday();

    long getAge(Date d);
}
