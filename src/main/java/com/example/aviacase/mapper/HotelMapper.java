package com.example.aviacase.mapper;

import com.example.aviacase.dto.HotelDto;
import com.example.aviacase.model.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelMapper {
    HotelDto toHotelDto(Hotel hotel);
    Hotel fromHotelDto(HotelDto hotelDto);
}
