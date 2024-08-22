package com.trannam.quizz.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.JOSEException;
import com.trannam.quizz.dto.request.AuthenticationRequest;
import com.trannam.quizz.dto.request.IntrospectRequest;
import com.trannam.quizz.dto.response.ApiResponse;
import com.trannam.quizz.dto.response.AuthenticationResponse;
import com.trannam.quizz.dto.response.IntrospectResponse;
import com.trannam.quizz.services.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.login(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .code(1000)
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var response = authenticationService.verifyToken(request.getToken());
        return ApiResponse.<IntrospectResponse>builder()
                .code(1000)
                .result(response)
                .build();
    }
}
