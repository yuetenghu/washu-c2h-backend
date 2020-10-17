package com.yuetenghu.washuc2hbackend.trip;

import com.yuetenghu.washuc2hbackend.addr.Addr;
import com.yuetenghu.washuc2hbackend.PasscodeManager;
import com.yuetenghu.washuc2hbackend.driver.Driver;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
public class Trip {

    @Id
    @GeneratedValue
    private Integer id;
    // private int driverId;
    private String passcode;
    private Calendar startTime;
    private Calendar finishTime;
    private boolean isRouteUpToDate;
    @OneToMany(
            mappedBy = "trip",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Addr> route;
    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;

    protected Trip() {

    }

    public Trip(Calendar startTime, boolean isRouteUpToDate, List<Addr> route) {
        // this.driverId = driverId;
        this.passcode = PasscodeManager.generateNewPasscode();
        this.startTime = startTime;
        this.finishTime = null;
        this.isRouteUpToDate = isRouteUpToDate;
        this.route = route;
    }

    public int getId() {return this.id;}
    // public int getDriverId() {return this.driver.getId();}
    public String getPasscode() {return this.passcode;}
    public Calendar getStartTime() {return this.startTime;}
    public Calendar getFinishTime() {return this.finishTime;}
    public boolean getIsRouteUpToDate() {return this.isRouteUpToDate;}
    public List<Addr> getRoute() {return this.route;}

    public void setPasscode() {this.passcode = PasscodeManager.generateNewPasscode();}
    public void setFinishTime() {
        if (this.getFinishTime() == null) this.setFinishTime(Calendar.getInstance());
    }
    public void setFinishTime(Calendar finishTime) {
        if (this.getFinishTime() == null) this.finishTime = finishTime;
    }
    public void setDriver(Driver driver) {this.driver = driver;};

    public Addr addAddr(Addr addr) {
        this.route.add(addr);
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
    public boolean setRoute(List<Addr> route) {
        this.route = route;
        this.isRouteUpToDate = true;
        return true;
    }

}
