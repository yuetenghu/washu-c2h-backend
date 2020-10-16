package com.yuetenghu.washuc2hbackend.trip;

import com.yuetenghu.washuc2hbackend.Addr;
import com.yuetenghu.washuc2hbackend.PasscodeManager;

import java.util.Calendar;
import java.util.TimeZone;

public class Trip {

    private final int id;  // TODO change to increment id; Annotation of JPA?
    private final int driverId;
    private final String passcode;
    private final Calendar startTime;
    private Calendar finishTime;
    private boolean isRouteUpToDate;
    private Addr[] route;

    public Trip(int id, int driverId, Calendar startTime, boolean isRouteUpToDate, Addr[] route) {
        this.id = id;
        this.driverId = driverId;
        this.passcode = PasscodeManager.generateNewPasscode();
        this.startTime = startTime;
        this.finishTime = null;
        this.isRouteUpToDate = isRouteUpToDate;
        this.route = route;
    }

    public int getId() {return this.id;}
    public int getDriverId() {return this.driverId;}
    public String getPasscode() {return this.passcode;}
    public Calendar getStartTime() {return this.startTime;}
    public Calendar getFinishTime() {return this.finishTime;}
    public boolean getIsRouteUpToDate() {return this.isRouteUpToDate;}
    public Addr[] getRoute() {return this.route;}

    public void setFinishTime() {
        if (this.getFinishTime() == null) this.setFinishTime(Calendar.getInstance());
    }
    public void setFinishTime(Calendar finishTime) {
        if (this.getFinishTime() == null) this.finishTime = finishTime;
    }

    public Addr addAddr(Addr addr) {
        Addr[] newRoute = new Addr[this.route.length + 1];
        System.arraycopy(this.route, 0, newRoute, 0, this.route.length);
        newRoute[newRoute.length - 1] = addr;
        this.isRouteUpToDate = false;
        this.route = newRoute;
        return addr;
    }

    public Addr findAddr(Addr addr) {
        for (Addr foundAddr : this.getRoute()) {
            if (foundAddr.equals(addr)) return foundAddr;
        }
        return null;
    }

    public Addr findAddr(int addrId) {
        for (Addr foundAddr : this.getRoute()) {
            if (foundAddr.getId() == addrId) return foundAddr;
        }
        return null;
    }

    public boolean removeAddr(Addr addr) {
        return true;
    }

    // public Addr updateAddrStatus(Addr addr) {
    //     addr.setArrivalTime(Calendar.getInstance());
    //     return addr;
    // }

    // Async, publish to MQ
    // Only use this after addAddr
    private boolean planRoute() {
        return true;
    }

    // Used for callback by RoutePlanner
    public boolean setRoute(Addr[] route) {
        this.route = route;
        this.isRouteUpToDate = true;
        return true;
    }

}
