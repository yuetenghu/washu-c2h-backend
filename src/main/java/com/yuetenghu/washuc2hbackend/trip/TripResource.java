package com.yuetenghu.washuc2hbackend.trip;

import com.yuetenghu.washuc2hbackend.addr.Addr;
import com.yuetenghu.washuc2hbackend.driver.Driver;
import com.yuetenghu.washuc2hbackend.driver.DriverJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TripResource {

    @Autowired
    private TripHardCodedService tripOldService;

    @Autowired
    private TripJpaRepository tripService;
    @Autowired
    private DriverJpaRepository driverService;

    @PostMapping("jpa/driver/trip")
    public ResponseEntity<List<Trip>> getAllTrips(@RequestBody Driver driver) {
        List<Trip> foundTripList = tripService.findByDriverId(driver.getId());
        return new ResponseEntity<>(foundTripList, HttpStatus.OK);
    }


    @GetMapping("jpa/trip/{id}")
    public ResponseEntity<Trip> getTrip(@PathVariable int id) {
        Optional<Trip> foundTrip = tripService.findById(id);
        if (foundTrip.isPresent()) return new ResponseEntity<>(foundTrip.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("jpa/rider/passcode/{passcode}")
    public ResponseEntity<Trip> verifyPasde(@PathVariable String passcode) {
        List<Trip> foundTripList = tripService.findByPasscode(passcode);
        if (foundTripList.size() > 0) {
            return new ResponseEntity<>(foundTripList.get(foundTripList.size() - 1), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("jpa/driver/{driverId}/trip/new")
    public ResponseEntity<Trip> createTrip(@PathVariable int driverId, @RequestBody Trip trip) {
        Optional<Driver> foundDriver = driverService.findById(driverId);
        if (foundDriver.isPresent()) {
            trip.setDriver(foundDriver.get());
            trip.setPasscode();
            Trip createdTrip = tripService.save(trip);
            return new ResponseEntity<>(createdTrip, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("jpa/driver/trip/{id}")
    public ResponseEntity<Trip> finishTrip(@PathVariable int id) {
        Optional<Trip> foundTrip = tripService.findById(id);
        if (foundTrip.isPresent()) {
            foundTrip.get().setFinishTime();
            tripService.save(foundTrip.get());
            return new ResponseEntity<>(foundTrip.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Moved to AddrResource
    // @PostMapping("jpa/rider/trip/{tripId}/addr/new")
    // public ResponseEntity<Addr> createAddr(@PathVariable int tripId, @RequestBody Addr addr) {
    //     Trip foundTrip = tripOldService.findById(tripId);
    //     if (foundTrip != null) {
    //         Addr createdAddr = foundTrip.addAddr(addr);
    //         return new ResponseEntity<>(createdAddr, HttpStatus.OK);
    //     }
    //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }

    // Moved to AddrResource
    // @PutMapping("jpa/driver/trip/{tripId}/addr/{addrId}")
    // public ResponseEntity<Addr> updateAddr(@PathVariable int tripId, @PathVariable int addrId) {
    //     Trip foundTrip = tripOldService.findById(tripId);
    //     if (foundTrip != null) {
    //         Addr foundAddr = foundTrip.findAddr(addrId); {
    //             if (foundAddr != null) {
    //                 foundAddr.setArrivalTime();
    //                 return new ResponseEntity<>(foundAddr, HttpStatus.OK);
    //             }
    //         }
    //     }
    //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }

}
