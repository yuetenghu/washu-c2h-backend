package com.yuetenghu.washuc2hbackend.rider;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RiderHardCodedService {
    private static int idCounter = 201;
    private static List<Rider> riders = new ArrayList<>();

    // static {
    //     riders.add(new Rider(104, "Bagel", "Ctb", Rider.Status.GRAD, Rider.Station.MALLINCKRODT));
    // }

    public Rider save(Rider rider) {
        // TODO: rider.getStatus()/getStation() might not work for JS
        Rider createdRider = new Rider(idCounter++, rider.getSurname(), rider.getGivenName(), rider.getStatus(), rider.getStation());
        riders.add(createdRider);
        return createdRider;
    }

    public List<Rider> getAllRiders() {
        return riders;
    }
}
