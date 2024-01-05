package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class RoomInfoDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private List<RoomAvailabilityDTO> roomAvailability;
    private List<RoomRateDTO> roomRate;
}
