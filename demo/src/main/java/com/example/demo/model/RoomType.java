package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="room_type")
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_type_id")
    private Long id;
    @Column(name = "room_type_name")
    private String roomTypeName;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
