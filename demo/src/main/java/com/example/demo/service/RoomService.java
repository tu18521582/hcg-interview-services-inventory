package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.exception.CommonException;
import com.example.demo.model.RoomAvailability;
import com.example.demo.model.RoomRate;
import com.example.demo.repository.RoomAvailabilityRepository;
import com.example.demo.repository.RoomRateRepository;
import com.example.demo.utils.CommonUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRateRepository roomRateRepository;

    @Autowired
    private RoomAvailabilityRepository roomAvailabilityRepository;

    public RoomInfoDTO getRoomInfo(LocalDate checkInDate, LocalDate checkOutDate) {
        List<RoomRate> roomRates = roomRateRepository.findByDateBetween(checkInDate, checkOutDate);
        List<RoomAvailability> roomAvailabilities = roomAvailabilityRepository.findByDateBetween(checkInDate, checkOutDate);

        return RoomInfoDTO.builder()
                .checkInDate(checkInDate)
                .checkOutDate(checkOutDate)
                .roomAvailability(CommonUtils.mapRoomAvailabilityEntityListToDTOList(roomAvailabilities))
                .roomRate(CommonUtils.mapRoomRateEntityListToDTOList(roomRates))
                .build();
    }

    public BigDecimal getPriceRoomAvailability(Long roomTypeId, Long ratePlanId, LocalDate checkInDate, LocalDate checkOutDate) {
        List<RoomAvailability> roomAvailabilities = roomAvailabilityRepository.findByRoomTypeIdAndDateBetween(roomTypeId, checkInDate, checkOutDate);
        BigDecimal priceWithInvalidRoom = BigDecimal.valueOf(-1);
        if (roomAvailabilities.size() == 0) {
            return priceWithInvalidRoom;
        }

        for (RoomAvailability roomAvailability : roomAvailabilities) {
            if (roomAvailability.getNumberOfRoomsAvailable() < 1) {
                return priceWithInvalidRoom;
            }
        }

        List<RoomRate> roomRates = roomRateRepository.findByRatePlanIdAndRoomTypeIdAndDateBetween(ratePlanId, roomTypeId, checkInDate, checkOutDate);
        if (roomRates.size() == 0) {
            return priceWithInvalidRoom;
        }

        BigDecimal totalPrice = BigDecimal.ZERO;
        for (RoomRate roomRate : roomRates) {
            totalPrice = totalPrice.add(roomRate.getPrice());
        }
        return totalPrice;
    }

    public void updateRoomAvailabilityAfterBooking(Long roomTypeId, LocalDate checkInDate, LocalDate checkOutDate) {
        List<RoomAvailability> roomAvailabilities = roomAvailabilityRepository.findByRoomTypeIdAndDateBetween(roomTypeId, checkInDate, checkOutDate);

        for (RoomAvailability roomAvailability : roomAvailabilities) {
            roomAvailability.setNumberOfRoomsAvailable(roomAvailability.getNumberOfRoomsAvailable() - 1);
            roomAvailabilityRepository.save(roomAvailability);
        }
    }

    @Transactional
    public void updateRoomAvailabilityAfterChangeDate(Long roomTypeId, LocalDate oldCheckInDate, LocalDate oldCheckOutDate, LocalDate newCheckInDate, LocalDate newCheckOutDate) {
        validateDatesAvailability(roomTypeId, newCheckOutDate, newCheckInDate);

        // Calculate date ranges for old and new bookings
        List<LocalDate> oldBookedDates = getDateRange(oldCheckInDate, oldCheckOutDate);
        List<LocalDate> newBookedDates = getDateRange(newCheckOutDate, newCheckInDate);

        // Iterate over dates and update availability accordingly
        for (LocalDate date : oldBookedDates) {
            RoomAvailability availability = roomAvailabilityRepository.findByRoomTypeIdAndDate(roomTypeId, date).orElseThrow(() -> new CommonException("Room availability not found"));
            availability.setNumberOfRoomsAvailable(availability.getNumberOfRoomsAvailable() + 1); // Release room for old dates
            roomAvailabilityRepository.save(availability);
        }

        for (LocalDate date : newBookedDates) {
            RoomAvailability availability = roomAvailabilityRepository.findByRoomTypeIdAndDate(roomTypeId, date).orElseThrow(() -> new CommonException("Room availability not found"));
            availability.setNumberOfRoomsAvailable(availability.getNumberOfRoomsAvailable() - 1); // Occupy room for new dates
            roomAvailabilityRepository.save(availability);
        }
    }

    private List<LocalDate> getDateRange(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            dates.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }
        return dates;
    }

    public void validateDatesAvailability(Long roomTypeId, LocalDate newCheckInDate, LocalDate newCheckOutDate) {
        // Basic date validation
        if (newCheckOutDate.isAfter(newCheckInDate)) {
            throw new CommonException("Check in date cannot be after check out date");
        }

        // Check for room availability
        List<LocalDate> bookedDates = getDateRange(newCheckInDate, newCheckOutDate);
        for (LocalDate date : bookedDates) {
            RoomAvailability availability = roomAvailabilityRepository.findByRoomTypeIdAndDate(roomTypeId, date).orElseThrow(() -> new IllegalStateException("Room availability not found"));
            if (availability.getNumberOfRoomsAvailable() <= 0) {
                throw new CommonException(String.format("Room not available on %s", date));
            }
        }
    }
}