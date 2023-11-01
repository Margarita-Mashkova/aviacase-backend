package com.example.aviacase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class FeedbackDto {
    private Long id;
    private String text;
    private int rate;
    @JsonFormat(pattern="dd MMM yyyy в HH:mm")
    private Date date;
    private UserDto user;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long tourId;
}
