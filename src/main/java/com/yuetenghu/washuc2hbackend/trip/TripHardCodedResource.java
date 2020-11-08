package com.yuetenghu.washuc2hbackend.trip;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yuetenghu.washuc2hbackend.addr.Addr;
import com.yuetenghu.washuc2hbackend.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TripHardCodedResource {

    @Autowired
    private TripHardCodedService tripService;

    @PostMapping("driver/trip")
    public ResponseEntity<List<Trip>> getAllTrips(@RequestBody Driver driver) {
        List<Trip> trips = tripService.findByDriverId(driver.getId());
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @PostMapping("driver/trip/new")
    public ResponseEntity<Trip> createTrip(@RequestBody Trip trip) {
        Trip createdTrip = tripService.save(trip);
        return new ResponseEntity<>(createdTrip, HttpStatus.OK);
    }

    @GetMapping("trip/{id}")
    public ResponseEntity<Trip> getTrip(@PathVariable int id) {
        return new ResponseEntity<>(tripService.findById(id), HttpStatus.OK);
    }

    @GetMapping("rider/passcode/{passcode}")
    public ResponseEntity<Trip> verifyPasscode(@PathVariable String passcode) {
        Trip foundTrip = tripService.findByPasscode(passcode);
        if (foundTrip != null) {
            return new ResponseEntity<>(foundTrip, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/driver/trip/{id}")
    public ResponseEntity<Trip> finishTrip(@PathVariable int id) {
        Trip foundTrip = tripService.findById(id);
        if (foundTrip != null) {
            foundTrip.setFinishTime();
            return new ResponseEntity<>(foundTrip, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // @PostMapping("rider/trip/{tripId}/addr/new")
    // public ResponseEntity<Addr> createAddr(@PathVariable int tripId, @RequestBody Addr addr) throws JsonProcessingException {
    //     Trip foundTrip = tripService.findById(tripId);
    //     if (foundTrip != null) {
    //         Addr createdAddr = foundTrip.addAddr(addr);
    //         return new ResponseEntity<>(createdAddr, HttpStatus.OK);
    //     }
    //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }

    @PutMapping("/driver/trip/{tripId}/addr/{addrId}")
    public ResponseEntity<Addr> updateAddr(@PathVariable int tripId, @PathVariable int addrId) {
        Trip foundTrip = tripService.findById(tripId);
        if (foundTrip != null) {
            Addr foundAddr = foundTrip.findAddr(addrId); {
                if (foundAddr != null) {
                    foundAddr.setArrivalTime();
                    return new ResponseEntity<>(foundAddr, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
