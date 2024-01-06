package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="rate_plan")
public class RatePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rate_plan_id")
    private Long id;
    @Column(name = "rate_plan_name")
    private String ratePlanName;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}