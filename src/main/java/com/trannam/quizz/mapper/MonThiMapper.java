package com.trannam.quizz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.trannam.quizz.dto.request.MonThiRequest;
import com.trannam.quizz.dto.response.MonThiResponse;
import com.trannam.quizz.entity.MonThi;

@Mapper(componentModel = "spring")
public interface MonThiMapper {
    MonThi toMonThi(MonThiRequest request);

    MonThiResponse toMonThiResponse(MonThi monThi);

    void updateMonThi(@MappingTarget MonThi monThi, MonThiRequest request);
}
