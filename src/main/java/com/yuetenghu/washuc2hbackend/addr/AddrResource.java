package com.yuetenghu.washuc2hbackend.addr;

import com.yuetenghu.washuc2hbackend.trip.Trip;
import com.yuetenghu.washuc2hbackend.trip.TripJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AddrResource {

    @Autowired
    private AddrJpaRepository addrService;
    @Autowired
    private TripJpaRepository tripService;


    @PostMapping("jpa/rider/trip/{tripId}/addr/new")
    public ResponseEntity<Addr> createAddr(@PathVariable int tripId, @RequestBody Addr addr) {
        Optional<Trip> foundTrip = tripService.findById(tripId);
        if (foundTrip.isPresent()) {
            addr.setTrip(foundTrip.get());
            addr.setBoardingTime();
            Addr createdAddr = addrService.save(addr);
            return new ResponseEntity<>(createdAddr, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // Trip foundTrip = addrService.findById(tripId);
        // if (foundTrip != null) {
        //     Addr createdAddr = foundTrip.addAddr(addr);
        //     return new ResponseEntity<>(createdAddr, HttpStatus.OK);
        // }
    }

    @PutMapping("jpa/driver/trip/{tripId}/addr/{addrId}")
    public ResponseEntity<Addr> updateAddr(@PathVariable int tripId, @PathVariable int addrId) {
        List<Addr> foundAddrList = addrService.findByTripIdAndId(tripId, addrId);
        if (foundAddrList.size() >= 1) {
            Addr foundAddr = foundAddrList.get(0);
            foundAddr.setArrivalTime();
            addrService.save(foundAddr);
            return new ResponseEntity<>(foundAddr, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
