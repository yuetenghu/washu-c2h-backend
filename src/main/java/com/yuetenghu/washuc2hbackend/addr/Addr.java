package com.yuetenghu.washuc2hbackend.addr;

import com.yuetenghu.washuc2hbackend.trip.Trip;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class Addr {

    @Id
    @GeneratedValue
    private int id;  // TODO change to increment id; Annotation of JPA?
    private int riderId;
    // private int tripId;
    private String addr;
    private double lat;
    private double lng;
    private boolean hasArrived;
    private Calendar boardingTime;  // TODO what type to use; Consider Timezone
    private Calendar arrivalTime;  // TODO what type to use; Consider Timezone
    private int seqId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Trip trip;

    // public Trip getTrip() {
    //     return trip;
    // }

    public void setTrip(Trip trip) {this.trip = trip;}

    protected Addr() {

    }

    public Addr(int riderId, /*int tripId,*/ String addr, double lat, double lng) {
        this.riderId = riderId;
        // this.tripId = tripId;
        this.addr = addr;
        this.lat = lat;
        this.lng = lng;
        this.hasArrived = false;
        this.boardingTime = Calendar.getInstance();
        this.arrivalTime = null;
        this.seqId = -1;
    }

    // public Addr(int riderId, int tripId, String addr, double lat, double lng, Calendar boardingTime) {
    //     this.id = idCounter++;
    //     this.riderId = riderId;
    //     this.tripId = tripId;
    //     this.addr = addr;
    //     this.lat = lat;
    //     this.lng = lng;
    //     this.hasArrived = false;
    //     this.boardingTime = boardingTime;
    //     this.arrivalTime = null;
    // }

    public int getId() {return this.id;}
    public int getRiderId() {return this.riderId;}
    // public int getTripId() {return this.tripId;}
    public String getAddr() {return this.addr;}
    public double getLat() {return this.lat;}
    public double getLng() {return this.lng;}
    public boolean getHasArrived() {return this.hasArrived;}
    public Calendar getBoardingTime() {return this.boardingTime;}
    public Calendar getArrivalTime() {return this.arrivalTime;}
    public int getSeqId() {return this.seqId;}

    public void setBoardingTime() {this.setBoardingTime(Calendar.getInstance());}
    public void setBoardingTime(Calendar boardingTime) {
        if (this.boardingTime != null) {
            this.boardingTime = boardingTime;
        }
    }
    public void setArrivalTime() {
        this.setArrivalTime(Calendar.getInstance());
    }
    public void setArrivalTime(Calendar arrivalTime) {
        if (!this.hasArrived || this.arrivalTime == null) {
            this.arrivalTime = arrivalTime;
            this.hasArrived = true;
        }
    }
    public void setSeqId(int seqId) {
        this.seqId = seqId;
    }
}
