package com.yuetenghu.washuc2hbackend;

import java.util.Calendar;

public class Addr {
    private int id;  // TODO change to increment id; Annotation of JPA?
    private final int riderId;
    private final int tripId;
    private String addr;
    private double lat;
    private double lng;
    private boolean hasArrived;
    private Calendar boardingTime;  // TODO what type to use; Consider Timezone
    private Calendar arrivalTime;  // TODO what type to use; Consider Timezone
    private static int idCounter = 1;

    public Addr(int riderId, int tripId, String addr, double lat, double lng) {
        this.id = idCounter++;
        this.riderId = riderId;
        this.tripId = tripId;
        this.addr = addr;
        this.lat = lat;
        this.lng = lng;
        this.hasArrived = false;
        this.boardingTime = Calendar.getInstance();
        this.arrivalTime = null;
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
    public int getTripId() {return this.tripId;}
    public String getAddr() {return this.addr;}
    public double getLat() {return this.lat;}
    public double getLng() {return this.lng;}
    public boolean getHasArrived() {return this.hasArrived;}
    public Calendar getBoardingTime() {return this.boardingTime;}
    public Calendar getArrivalTime() {return this.arrivalTime;}

    public void setArrivalTime() {
        this.setArrivalTime(Calendar.getInstance());
    }
    public void setArrivalTime(Calendar arrivalTime) {
        if (!this.hasArrived || this.arrivalTime == null) {
            this.arrivalTime = arrivalTime;
            this.hasArrived = true;
        }
    }
}
