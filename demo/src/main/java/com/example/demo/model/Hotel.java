package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Long hotelId;
    @Column(name = "hotel_name")
    private String hotelName;
    @Column(name = "address")
    private String address;
    @Column(name = "phone_number")
    private String phone_number;
    @OneToMany(mappedBy = "hotel")
    private List<RoomType> roomTypes;
}