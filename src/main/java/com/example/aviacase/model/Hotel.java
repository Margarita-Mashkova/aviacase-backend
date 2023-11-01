package com.example.aviacase.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String location;

    @NonNull
    private int star;

    @NonNull
    private boolean feed;

    //Price for 1 night in hotel
    @NonNull
    private float price;

    private List<String> photos;

    @ManyToMany(mappedBy = "hotels")
    public List<Tour> tours;
}
