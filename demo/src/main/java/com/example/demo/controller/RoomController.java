package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/list-available")
    public ResponseEntity<RoomInfoDTO> getRoomInfo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.NONE, pattern = "dd-MM-yyyy") LocalDate checkInDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd-MM-yyyy") LocalDate checkOutDate) {
        RoomInfoDTO roomInfo = roomService.getRoomInfo(checkInDate, checkOutDate);
        return ResponseEntity.ok(roomInfo);
    }

    @PostMapping("/availability-and-price-if-available")
    public ResponseEntity<BigDecimal> checkRoomAvailabilityAndGetPriceIfAvailable(
            @RequestBody ValidateRoomAvailabilityRequest request
    ) {
        BigDecimal priceRoomAvailability = roomService.getPriceRoomAvailability(request.getRoomTypeId(), request.getRatePlanId(), request.getCheckInDate(), request.getCheckOutDate());
        System.out.println("Response with price: " + priceRoomAvailability);
        return ResponseEntity.ok(priceRoomAvailability);
    }

    @PutMapping("/update-room-availability")
    public ResponseEntity<BigDecimal> updateRoomAvailability(
            @RequestBody UpdateRoomAvailabilityRequest request
    ) {
        roomService.updateRoomAvailabilityAfterBooking(request.getRoomTypeId(), request.getCheckInDate(), request.getCheckOutDate());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-room-availability-with-new-dates")
    public ResponseEntity<BigDecimal> updateRoomAvailabilityAfterChangeDate(
            @RequestBody ChangeBookingInfoRequest request
    ) {
        roomService.updateRoomAvailabilityAfterChangeDate(request.getRoomTypeId(), request.getOldCheckInDate(), request.getOldCheckOutDate(),
                request.getNewCheckInDate(), request.getNewCheckOutDate());
        return ResponseEntity.ok().build();
    }
}