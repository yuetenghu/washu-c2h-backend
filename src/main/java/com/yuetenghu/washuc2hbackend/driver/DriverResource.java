package com.yuetenghu.washuc2hbackend.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DriverResource {

    @Autowired
    private DriverJpaRepository driverService;

    @PostMapping("jpa/driver/auth")
    public ResponseEntity<Driver> driverAuth(@RequestBody Driver driver) {
        List<Driver> foundDrivers = driverService.findBySurnameAndGivenName(driver.getSurname(), driver.getGivenName());
        if (foundDrivers.size() >= 1) {
            return new ResponseEntity<>(foundDrivers.get(0), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
