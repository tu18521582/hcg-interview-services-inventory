package com.example.demo.controller;

import com.example.demo.dto.RoomInfoDTO;
import com.example.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/room-info")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomInfoDTO> getRoomInfo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.NONE, pattern = "dd-MM-yyyy") LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd-MM-yyyy") LocalDate endDate) {
        RoomInfoDTO roomInfoList = roomService.getRoomInfo(startDate, endDate);
        return ResponseEntity.ok(roomInfoList);
    }
}