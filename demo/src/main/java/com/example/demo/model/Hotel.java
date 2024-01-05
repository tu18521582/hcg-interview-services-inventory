package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
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
    @Column(name = "other_hotel_information")
    private String otherHotelInformation;
    @OneToMany(mappedBy = "hotel")
    private List<RoomType> roomTypes;
}