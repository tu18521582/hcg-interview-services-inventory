package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.RoomAvailability;
import com.example.demo.model.RoomRate;
import com.example.demo.repository.RoomAvailabilityRepository;
import com.example.demo.repository.RoomRateRepository;
import com.example.demo.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRateRepository roomRateRepository;

    @Autowired
    private RoomAvailabilityRepository roomAvailabilityRepository;

    public RoomInfoDTO getRoomInfo(LocalDate startDate, LocalDate endDate) {
        List<RoomRate> roomRates = roomRateRepository.findByDateBetween(startDate, endDate);
        List<RoomAvailability> roomAvailabilities = roomAvailabilityRepository.findByDateBetween(startDate, endDate);

        return RoomInfoDTO.builder()
                .startDate(startDate)
                .endDate(endDate)
                .roomAvailability(CommonUtils.mapRoomAvailabilityEntityListToDTOList(roomAvailabilities))
                .roomRate(CommonUtils.mapRoomRateEntityListToDTOList(roomRates))
                .build();
    }
}