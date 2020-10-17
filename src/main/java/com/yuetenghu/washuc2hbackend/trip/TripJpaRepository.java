package com.yuetenghu.washuc2hbackend.trip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripJpaRepository extends JpaRepository<Trip, Integer> {
    List<Trip> findByDriverId(int driverId);
    List<Trip> findByPasscode(String passcode);
}
