package com.example.aviacase.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class MakePurchaseDto {
    private Long id;
    private Date date;
    private int nights;
    private int tourists;
    private float sum;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long hotelId;
    private UserDto user;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long tourId;
}
