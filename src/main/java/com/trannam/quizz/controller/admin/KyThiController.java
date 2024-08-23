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

import com.trannam.quizz.dto.request.KyThiRequest;
import com.trannam.quizz.dto.response.ApiResponse;
import com.trannam.quizz.dto.response.KyThiDetailResponse;
import com.trannam.quizz.dto.response.KyThiResponse;
import com.trannam.quizz.services.KyThiService;

@RestController
@RequestMapping("/admin")
public class KyThiController {
    @Autowired
    private KyThiService kyThiService;

    @PostMapping("/createKyThi")
    ApiResponse<KyThiResponse> createKyThi(@RequestBody KyThiRequest kyThi) {
        return ApiResponse.<KyThiResponse>builder()
                .code(1000)
                .result(kyThiService.createKyThi(kyThi))
                .build();
    }

    @PutMapping("/updateKyThi/{kyThiId}")
    ApiResponse<KyThiResponse> updateKyThi(@PathVariable Long kyThiId, @RequestBody KyThiRequest kyThi) {
        return ApiResponse.<KyThiResponse>builder()
                .code(1000)
                .result(kyThiService.updateKyThi(kyThiId, kyThi))
                .build();
    }

    @DeleteMapping("/deleteKyThi/{kyThiId}")
    ApiResponse<String> deleteMonThi(@PathVariable Long kyThiId) {
        return ApiResponse.<String>builder()
                .message(kyThiService.deleteKyThi(kyThiId))
                .build();
    }

    @GetMapping("/getAllKyThi")
    ApiResponse<List<KyThiDetailResponse>> getAllKyThi() {
        return ApiResponse.<List<KyThiDetailResponse>>builder()
                .code(1000)
                .result(kyThiService.getAllKyThi())
                .build();
    }
}
