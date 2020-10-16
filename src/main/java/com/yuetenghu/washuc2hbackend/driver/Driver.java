package com.yuetenghu.washuc2hbackend.driver;

import com.yuetenghu.washuc2hbackend.Account;
import com.yuetenghu.washuc2hbackend.Addr;
import com.yuetenghu.washuc2hbackend.PeopleEnRoute;
import com.yuetenghu.washuc2hbackend.trip.Trip;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class Driver extends Account implements PeopleEnRoute {

    public Driver(int id, String surname, String givenName) {
        super(id, surname, givenName);
    }

    public List<Trip> getTrips() {
        List<Trip> list = new LinkedList<>();
        return list;
    }

    public Trip getCurrentTrip() {
        Addr addrIthacaTofu = new Addr(101, 11,"23 Cinema Dr, Ithaca, NY", 42.479702, -76.477670);
        Addr addrSummerhill = new Addr(102, 11,"210 Summerhill Dr, Ithaca, NY", 42.437409, -76.459810);
        Trip trip = new Trip(1, 22, Calendar.getInstance(), false, new Addr[] {addrIthacaTofu, addrSummerhill});
        return trip;
    }

    // TODO
    // public Trip addTrip() {
    //     Trip trip = new Trip();
    //     return trip;
    // }
    //
    // public Trip continueTrip() {
    //     Trip trip = new Trip();
    //     return trip;
    // }

}
