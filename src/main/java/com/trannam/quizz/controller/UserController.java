package com.trannam.quizz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trannam.quizz.dto.request.UserCreationRequest;
import com.trannam.quizz.dto.request.UserUpdateRequest;
import com.trannam.quizz.dto.response.ApiResponse;
import com.trannam.quizz.dto.response.UserResponse;
import com.trannam.quizz.services.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/creatUser")
    ApiResponse<UserResponse> creatUser(@RequestBody @Valid UserCreationRequest user) {
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userService.createUser(user))
                .build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUsers() {
        List<UserResponse> res = userService.getUsers();
        return ApiResponse.<List<UserResponse>>builder()
                .code(1000)
                .result(res)
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable Long userId) {
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userService.getUser(userId))
                .build();
    }

    @PutMapping("update/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable Long userId, @RequestBody UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userService.updateUser(userId, request))
                .build();
    }

    @DeleteMapping("/delete/{userId}")
    ApiResponse<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ApiResponse.<String>builder()
                .code(1000)
                .message("Successfull deleted user with id = " + userId)
                .build();
    }
}
