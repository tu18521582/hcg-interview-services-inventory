package com.example.demo.repository;

import com.example.demo.model.RoomAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomAvailabilityRepository extends JpaRepository<RoomAvailability, Long> {
    List<RoomAvailability> findByDateBetween(LocalDate checkInDate, LocalDate checkOutDate);
    List<RoomAvailability> findByRoomTypeIdAndDateBetween(Long roomTypeId, LocalDate checkInDate, LocalDate checkOutDate);
    Optional<RoomAvailability> findByRoomTypeIdAndDate(Long roomTypeId, LocalDate date);
}
