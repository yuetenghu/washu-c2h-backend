package com.yuetenghu.washuc2hbackend.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DriverHardCodedResource {

    @Autowired
    private DriverHardCodedService driverService;

    @PostMapping("/driver/auth")
    public ResponseEntity<Driver> driverAuth(@RequestBody Driver driver) {
        Driver foundDriver =  driverService.findByNames(driver.getSurname(), driver.getGivenName());
        return (foundDriver != null) ? new ResponseEntity<>(foundDriver, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
