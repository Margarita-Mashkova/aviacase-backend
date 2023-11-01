package com.example.aviacase.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String country;
    @NonNull
    private Date startDate;

    //min price per tour without taking into account the customer's parameters
    @NonNull
    private float price;

    private String photo;

    @OneToMany
    @JoinColumn(name = "tour_id")
    private List<Purchase> purchases;

    @OneToMany
    @JoinColumn(name = "tour_id")
    private List<Feedback> feedbacks;

    @ManyToMany
    @JoinTable(
            name = "tour_hotel",
            joinColumns = { @JoinColumn(name = "tour_id") },
            inverseJoinColumns = { @JoinColumn(name = "hotel_id") }
    )
    private List<Hotel> hotels;
}
