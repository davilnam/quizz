package com.trannam.quizz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.trannam.quizz.dto.request.KyThiRequest;
import com.trannam.quizz.dto.response.KyThiResponse;
import com.trannam.quizz.entity.KyThi;

@Mapper(componentModel = "spring")
public interface KyThiMapper {
    KyThi toKyThi(KyThiRequest request);

    KyThiResponse toKyThiResponse(KyThi kyThi);

    void updateKyThi(@MappingTarget KyThi kyThi, KyThiRequest request);
}
