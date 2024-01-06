package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name="room_rate")
public class RoomRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_rate_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rate_plan_id")
    private RatePlan ratePlan;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;

    @Column(name = "date")
    private LocalDate date;
    @Column(name = "price")
    private BigDecimal price;
}
