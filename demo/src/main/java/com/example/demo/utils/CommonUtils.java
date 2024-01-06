package com.example.demo.utils;

import com.example.demo.model.RoomAvailability;
import com.example.demo.dto.RoomAvailabilityDTO;
import com.example.demo.model.RoomRate;
import com.example.demo.dto.RoomRateDTO;

import java.util.ArrayList;
import java.util.List;

public class CommonUtils {
    public static RoomRateDTO mapRoomRateEntityToDTO(RoomRate roomRate) {
        return RoomRateDTO.builder()
                .roomTypeId(roomRate.getRoomType().getId())
                .ratePlanId(roomRate.getRatePlan().getId())
                .price(roomRate.getPrice())
                .build();
    }

    public static RoomAvailabilityDTO mapRoomAvailabilityEntityToDTO(RoomAvailability roomAvailability) {
        return RoomAvailabilityDTO.builder()
                .roomTypeId(roomAvailability.getRoomType().getId())
                .availableToSell(roomAvailability.getNumberOfRoomsAvailable())
                .build();
    }

    public static List<RoomRateDTO> mapRoomRateEntityListToDTOList(List<RoomRate> roomRates) {
        List<RoomRateDTO> roomRateDTOs = new ArrayList<>();
        roomRates.forEach(item -> {
            roomRateDTOs.add(mapRoomRateEntityToDTO(item));
        });

        return roomRateDTOs;
    }

    public static List<RoomAvailabilityDTO> mapRoomAvailabilityEntityListToDTOList(List<RoomAvailability> roomAvailabilities) {
        List<RoomAvailabilityDTO> roomAvailabilityDTOs = new ArrayList<>();
        roomAvailabilities.forEach(item -> {
            roomAvailabilityDTOs.add(mapRoomAvailabilityEntityToDTO(item));
        });

        return roomAvailabilityDTOs;
    }
}
