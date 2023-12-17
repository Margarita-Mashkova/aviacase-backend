package com.example.aviacase.mapper;

import com.example.aviacase.dto.TourDto;
import com.example.aviacase.dto.TourInfoDto;
import com.example.aviacase.model.Tour;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TourMapper {
    Tour fromTourDto(TourDto tourDto);
    TourDto toTourDto(Tour tour);
    Tour fromTourInfoDto(TourInfoDto tourInfoDto);
    TourInfoDto toTourInfoDto(Tour tour);
}
