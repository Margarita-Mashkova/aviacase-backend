package com.example.aviacase.mapper;

import com.example.aviacase.dto.FeedbackDto;
import com.example.aviacase.model.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FeedbackMapper {
    Feedback fromFeedbackDto(FeedbackDto feedbackDto);
    FeedbackDto toFeedbackDto(Feedback feedback);
}
