package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@Table(name="room_availability")
public class RoomAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_availability_id")
    private Long availabilityId;
    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "number_of_rooms_available")
    private Integer numberOfRoomsAvailable;
}
