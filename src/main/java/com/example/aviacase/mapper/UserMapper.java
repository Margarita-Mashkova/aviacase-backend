package com.example.aviacase.mapper;

import com.example.aviacase.dto.AuthDto;
import com.example.aviacase.dto.UserDto;
import com.example.aviacase.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserDto toUserDto (User user);
    User fromUserDto (UserDto user);
    User fromAuthDto(AuthDto authDto);
}
