package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Table(name="rate_plan")
public class RatePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rate_plan_id")
    private Long ratePlanId;

    @Column(name = "rate_plan_name")
    private String ratePlanName;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}