package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Table(name="room_type")
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_type_id")
    private Long roomTypeId;
    @Column(name = "room_type_name")
    private String roomTypeName;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
