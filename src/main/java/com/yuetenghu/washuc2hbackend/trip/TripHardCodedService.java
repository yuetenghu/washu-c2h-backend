package com.yuetenghu.washuc2hbackend.trip;

import com.yuetenghu.washuc2hbackend.Addr;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class TripHardCodedService {
    private static int idCounter = 1;
    private static List<Trip> trips = new ArrayList<>();
    private static Addr addrIthacaTofu = new Addr(101, 11, "23 Cinema Dr, Ithaca, NY", 42.479702, -76.477670);
    private static Addr addrSummerhill = new Addr(102, 11, "210 Summerhill Dr, Ithaca, NY", 42.437409, -76.459810);

    static {
        addrIthacaTofu.setArrivalTime(Calendar.getInstance());
        trips.add(new Trip(idCounter++, 22, Calendar.getInstance(), false, new Addr[] {addrIthacaTofu, addrSummerhill}));
        trips.add(new Trip(idCounter++, 22, Calendar.getInstance(), false, new Addr[] {addrIthacaTofu, addrSummerhill}));
    }

    public List<Trip> findByDriverId(int driverId) {
        List<Trip> foundTrips = new ArrayList<>();
        trips.forEach((trip) -> {if (trip.getDriverId() == driverId) foundTrips.add(trip);});
        return foundTrips;
    }

    public Trip findById(int id) {
        for (Trip trip: trips) {
            if (trip.getId() == id) {
                return trip;
            }
        }
        return null;
    }

    public Trip findByPasscode(String passcode) {
        for (int i = trips.size() - 1; i >= 0; --i) {
            if (trips.get(i).getPasscode().equals(passcode.toUpperCase())) return trips.get(i);
        }
        return null;
    }

    public Trip save(Trip trip) {
        Trip createdTrip = new Trip(idCounter++, trip.getDriverId(), trip.getStartTime()/*Calendar.getInstance()*/, false, new Addr[] {});
        trips.add(createdTrip);
        return createdTrip;
    }

    // public Addr findAddr(int tripId, int addrId) {
    //     if (this.findById(tripId) != null) {
    //         return this.findById(tripId).findAddr(addrId);
    //     }
    //     return null;
    // }

    // public Addr updateAddrStatus(Trip trip, Addr addr) {
    //     return trip.updateAddrStatus(addr);
    // }
}
