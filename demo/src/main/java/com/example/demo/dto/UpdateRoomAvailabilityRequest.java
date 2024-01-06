package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateRoomAvailabilityRequest {
    private Long roomTypeId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
