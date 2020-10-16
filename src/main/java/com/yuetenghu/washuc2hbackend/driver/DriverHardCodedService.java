package com.yuetenghu.washuc2hbackend.driver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverHardCodedService {
    private static List<Driver> drivers = new ArrayList<>();

    static {
        drivers.add(new Driver(22, "Who", "Doctor"));
    }

    public Driver findByNames(String surname, String givenName) {
        for (Driver driver: drivers) {
            if (driver.getSurname().equals(surname) && driver.getGivenName().equals(givenName)) return driver;
        }
        return null;
    }

}
