package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ChangeBookingInfoRequest {
    private Long roomTypeId;
    private LocalDate oldCheckInDate;
    private LocalDate oldCheckOutDate;
    private LocalDate newCheckInDate;
    private LocalDate newCheckOutDate;
}
