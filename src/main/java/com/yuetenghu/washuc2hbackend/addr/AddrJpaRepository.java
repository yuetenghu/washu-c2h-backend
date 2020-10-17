package com.yuetenghu.washuc2hbackend.addr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddrJpaRepository extends JpaRepository<Addr, Integer> {
    List<Addr> findByTripIdAndId(int tripId, int id);
}
