package com.example.aviacase.dto;

import lombok.Data;

@Data
public class HotelDto {
    private Long id;
    private String name;
    private String location;
    private int star;
    private Boolean feed;
    private float price;
    private String photo;
}
