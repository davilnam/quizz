package com.trannam.quizz.services;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trannam.quizz.dto.request.UserCreationRequest;
import com.trannam.quizz.dto.request.UserUpdateRequest;
import com.trannam.quizz.dto.response.UserResponse;
import com.trannam.quizz.entity.User;
import com.trannam.quizz.enums.Role;
import com.trannam.quizz.exception.AppException;
import com.trannam.quizz.exception.ErrorCode;
import com.trannam.quizz.mapper.UserMapper;
import com.trannam.quizz.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

    public UserResponse getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(Long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        if (request.getPassword().equals("")) {
            request.setPassword(user.getPassword());
            userMapper.updateUser(user, request);
        } else {
            userMapper.updateUser(user, request);
        }
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        user.getResults().clear();
        userRepository.deleteById(userId);
    }
}
