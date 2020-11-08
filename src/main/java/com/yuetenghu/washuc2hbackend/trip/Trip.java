package com.yuetenghu.washuc2hbackend.trip;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuetenghu.washuc2hbackend.addr.Addr;
import com.yuetenghu.washuc2hbackend.PasscodeManager;
import com.yuetenghu.washuc2hbackend.driver.Driver;

import javax.persistence.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

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
    public void setIsRouteUpToDate(boolean isRouteUpToDate) {this.isRouteUpToDate = isRouteUpToDate;}
    public void setDriver(Driver driver) {this.driver = driver;};

    public void afterAddAddr() throws Exception {
        this.setIsRouteUpToDate(false);

        // Call API of Route Optimisation (Will call back and trigger setRoute())
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(this);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8000/route-optimise"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.discarding());
        // Now all addresses (incl new one) is added to MQ
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

    // Used for callback by RoutePlanner
    public void updateRoute(ArrayList<Integer> seqIds) {
        // From: [6, 2, 4], To: {6: No1, 2: No2, 4: No3}
        Map<Integer, Integer> addrIdToSeqId = new HashMap<>();
        for (int seqId = 0; seqId < seqIds.size(); ++seqId) {
            addrIdToSeqId.put(seqIds.get(seqId), seqId + 1);
        }
        for (Addr addr : this.route) {
            addr.setSeqId(addrIdToSeqId.get(addr.getId()));
        }
        this.isRouteUpToDate = true;
    }

}
