package com.yuetenghu.washuc2hbackend.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverJpaRepository extends JpaRepository<Driver, Integer> {
    List<Driver> findBySurnameAndGivenName(String surname, String givenName);
}
