package com.trannam.quizz.controller.admin;

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

import com.trannam.quizz.dto.request.MonThiRequest;
import com.trannam.quizz.dto.response.ApiResponse;
import com.trannam.quizz.dto.response.MonThiDeTailResponse;
import com.trannam.quizz.dto.response.MonThiResponse;
import com.trannam.quizz.services.MonThiService;

@RestController
@RequestMapping("/admin")
public class MonThiController {
    @Autowired
    private MonThiService monThiService;

    @PostMapping("/createMonThi")
    ApiResponse<MonThiResponse> createMonThi(@RequestBody MonThiRequest request) {
        return ApiResponse.<MonThiResponse>builder()
                .code(1000)
                .result(monThiService.createMonThi(request))
                .build();
    }

    @PutMapping("/updateMonThi/{monThiId}")
    ApiResponse<MonThiResponse> updateMonThi(@PathVariable Long monThiId, @RequestBody MonThiRequest request) {
        ApiResponse<MonThiResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(monThiService.updateMonThi(monThiId, request));
        apiResponse.setCode(1000);
        return apiResponse;
    }

    @DeleteMapping("/deleteMonThi/{monThiId}")
    ApiResponse<String> deleteMonThi(@PathVariable Long monThiId) {
        return ApiResponse.<String>builder()
                .code(1000)
                .message(monThiService.deleteMonThi(monThiId))
                .build();
    }

    @GetMapping("/getAllMonThi")
    ApiResponse<List<MonThiDeTailResponse>> getAllMonThi() {
        return ApiResponse.<List<MonThiDeTailResponse>>builder()
                .code(1000)
                .result(monThiService.getAllMonThi())
                .build();
    }
}
