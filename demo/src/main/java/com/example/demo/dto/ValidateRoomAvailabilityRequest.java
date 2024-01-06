package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ValidateRoomAvailabilityRequest {
    private Long roomTypeId;
    private Long ratePlanId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
