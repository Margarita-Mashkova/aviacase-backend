package com.example.aviacase.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PurchaseDto {
    private Long id;
    private Date date;
    private int nights;
    private int tourists;
    private float sum;
    private HotelDto hotel;
    private UserDto user;
    private TourInfoDto tour;

}
