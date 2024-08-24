package com.trannam.quizz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trannam.quizz.dto.request.MonThiRequest;
import com.trannam.quizz.dto.response.MonThiDeTailResponse;
import com.trannam.quizz.dto.response.MonThiResponse;
import com.trannam.quizz.entity.MonThi;
import com.trannam.quizz.exception.AppException;
import com.trannam.quizz.exception.ErrorCode;
import com.trannam.quizz.mapper.MonThiMapper;
import com.trannam.quizz.repository.MonThiRepository;

@Service
public class MonThiService {
    @Autowired
    private MonThiRepository monThiRepository;

    @Autowired
    private MonThiMapper monThiMapper;

    public MonThiResponse createMonThi(MonThiRequest request) {
        if (monThiRepository.existsByTenMonThi(request.getTenMonThi())) {
            throw new AppException(ErrorCode.MONTHI_EXISTED);
        }

        MonThi monThi = monThiMapper.toMonThi(request);

        return monThiMapper.toMonThiResponse(monThiRepository.save(monThi));
    }

    public MonThiResponse updateMonThi(long monThiId, MonThiRequest request) {
        MonThi monThi = monThiRepository.findById(monThiId)
                .orElseThrow(() -> new AppException(ErrorCode.MONTHI_NOT_EXISTED));
        monThiMapper.updateMonThi(monThi, request);
        return monThiMapper.toMonThiResponse(monThiRepository.save(monThi));
    }

    public String deleteMonThi(long monThiId) {
        MonThi monThi = monThiRepository.findById(monThiId)
                .orElseThrow(() -> new AppException(ErrorCode.MONTHI_NOT_EXISTED));
        String message = "";
        if (monThi.getDeThi().isEmpty()) {
            monThiRepository.deleteById(monThiId);
            message = "Đã xóa môn thi có id = " + monThiId;
        } else {
            message = "Không thể xóa do môn thi đã chứa đề thi!";
        }
        return message;
    }

    public List<MonThiDeTailResponse> getAllMonThi() {
        List<MonThi> dsMonThi = monThiRepository.findAll();
        List<MonThiDeTailResponse> res = dsMonThi.stream().map((monThi) -> {
            return MonThiDeTailResponse.builder()
                    .id(monThi.getId())
                    .tenMonThi(monThi.getTenMonThi())
                    .soLuongDeThi(monThi.getDeThi().size())
                    .build();
        }).toList();
        return res;
    }
}
