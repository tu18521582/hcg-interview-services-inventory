package com.example.demo.repository;

import com.example.demo.model.RoomRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRateRepository extends JpaRepository<RoomRate, Long> {
    List<RoomRate> findByDateBetween(LocalDate checkInDate, LocalDate checkOutDate);
    List<RoomRate> findByRatePlanIdAndRoomTypeIdAndDateBetween(Long ratePlanId, Long roomTypeId, LocalDate checkInDate, LocalDate checkOutDate);
}
