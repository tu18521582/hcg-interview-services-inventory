package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomAvailabilityDTO {
    private Long roomTypeId;
    private Integer availableToSell;
}
