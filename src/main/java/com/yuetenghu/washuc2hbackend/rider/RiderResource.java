package com.yuetenghu.washuc2hbackend.rider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://localhost:4200")
public class RiderResource {

    @Autowired
    private RiderHardCodedService riderOldService;

    @Autowired
    private RiderJpaRepository riderService;

    @PostMapping("jpa/rider/new")
    public ResponseEntity<Rider> createRider(@RequestBody Rider rider) {
        Rider createdRider = riderService.save(rider);
        return new ResponseEntity<>(createdRider, HttpStatus.OK);
    }

    @GetMapping("jpa/rider/all")
    public ResponseEntity<List<Rider>> getAllRiders() {
        return new ResponseEntity<>(riderService.findAll(), HttpStatus.OK);
    }
}
