package com.yuetenghu.washuc2hbackend.driver;

import com.yuetenghu.washuc2hbackend.Account;
import com.yuetenghu.washuc2hbackend.PeopleEnRoute;
import com.yuetenghu.washuc2hbackend.trip.Trip;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Driver extends Account implements PeopleEnRoute {

    @OneToMany(
            mappedBy = "driver",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Trip> trips;

    protected Driver() {

    }

    public Driver(String surname, String givenName) {
        super(surname, givenName);
    }

    // public List<Trip> getTrips() {
    //     List<Trip> list = new LinkedList<>();
    //     return list;
    // }

    // public Trip getCurrentTrip() {
    //     Addr addrIthacaTofu = new Addr(101, /*11,*/"23 Cinema Dr, Ithaca, NY", 42.479702, -76.477670);
    //     Addr addrSummerhill = new Addr(102, /*11,*/"210 Summerhill Dr, Ithaca, NY", 42.437409, -76.459810);
    //     List<Addr> tempAddrList = new LinkedList<>();
    //     tempAddrList.add(addrIthacaTofu);
    //     tempAddrList.add(addrSummerhill);
    //     Trip trip = new Trip(1, Calendar.getInstance(), false, tempAddrList);
    //     return trip;
    // }

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
