package com.trannam.quizz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.trannam.quizz.dto.request.UserCreationRequest;
import com.trannam.quizz.dto.request.UserUpdateRequest;
import com.trannam.quizz.dto.response.UserResponse;
import com.trannam.quizz.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    @Mapping(target = "id", ignore = false)
    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
