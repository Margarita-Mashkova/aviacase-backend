package com.example.aviacase.dto;

import com.example.aviacase.model.Hotel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TourDto {
    private Long id;
    private String name;
    private String country;
    private Date startDate;
    private float price;
    private String photo;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<FeedbackDto> feedbacks;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Hotel> hotels;
}
