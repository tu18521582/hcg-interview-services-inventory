package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class RoomRateDTO {
    private Long roomTypeId;
    private Long ratePlanId;
    private BigDecimal price;
}
