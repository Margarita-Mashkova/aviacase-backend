package com.example.aviacase.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TourInfoDto {
    private Long id;
    private String name;
    private String country;
    private Date startDate;
    private float price;
    private String photo;
}
