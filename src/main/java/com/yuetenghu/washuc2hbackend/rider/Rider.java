package com.yuetenghu.washuc2hbackend.rider;

import com.yuetenghu.washuc2hbackend.Account;
import com.yuetenghu.washuc2hbackend.PeopleEnRoute;

import javax.persistence.*;

@Entity
public class Rider extends Account implements PeopleEnRoute {

    public static enum Status {
        GRAD,
        UNDERGRAD,
        STAFF
    }

    public static enum Station {
        MALLINCKRODT,
        HOYT_FORSYTH
    }

    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Station station;

    protected Rider() {

    }

    public Rider(String surname, String givenName, Status status, Station station) {
        super(surname, givenName);
        this.status = status;
        this.station = station;
    }

    public Status getStatus() {return this.status;}
    public Station getStation() {return this.station;}

    // public boolean verifyPasscode(String passcode) {
    //     return true;
    // }

    // public Trip getCurrentTrip() {
    //     Addr addrIthacaTofu = new Addr(101,"23 Cinema Dr, Ithaca, NY", 42.479702, -76.477670, Calendar.getInstance());
    //     Addr addrSummerhill = new Addr(102, "210 Summerhill Dr, Ithaca, NY", 42.437409, -76.459810, Calendar.getInstance());
    //     Trip trip = new Trip(1, 22, Calendar.getInstance(), false, new Addr[] {addrIthacaTofu, addrSummerhill});
    //     return trip;
    // }

    // public Addr getCurrentAddr() {
    //     return new Addr(103,"Test St", 1.0, -1.0, Calendar.getInstance());
    // }

    // public Addr addAddrToTrip(Addr addr) {
    //     return addr;
    // }
}
