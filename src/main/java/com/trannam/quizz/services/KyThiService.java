package com.trannam.quizz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trannam.quizz.dto.request.KyThiRequest;
import com.trannam.quizz.dto.response.KyThiDetailResponse;
import com.trannam.quizz.dto.response.KyThiResponse;
import com.trannam.quizz.entity.DeThi;
import com.trannam.quizz.entity.KyThi;
import com.trannam.quizz.exception.AppException;
import com.trannam.quizz.exception.ErrorCode;
import com.trannam.quizz.mapper.KyThiMapper;
import com.trannam.quizz.repository.KyThiRepository;

@Service
public class KyThiService {
    @Autowired
    private KyThiRepository kyThiRepository;

    @Autowired
    private KyThiMapper kyThiMapper;

    public KyThiResponse createKyThi(KyThiRequest request) {
        if (kyThiRepository.existsByTenKyThi(request.getTenKyThi())) {
            throw new AppException(ErrorCode.KYTHI_EXISTED);
        }

        KyThi kyThi = kyThiMapper.toKyThi(request);

        return kyThiMapper.toKyThiResponse(kyThiRepository.save(kyThi));
    }

    public KyThiResponse updateKyThi(Long kythiId, KyThiRequest request) {

        KyThi kyThi = kyThiRepository.findById(kythiId)
                .orElseThrow(() -> new AppException(ErrorCode.KYTHI_NOT_EXISTED));
        kyThiMapper.updateKyThi(kyThi, request);
        return kyThiMapper.toKyThiResponse(kyThiRepository.save(kyThi));
    }

    public String deleteKyThi(Long kyThiId) {
        KyThi kyThi = kyThiRepository.findById(kyThiId)
                .orElseThrow(() -> new AppException(ErrorCode.KYTHI_NOT_EXISTED));
        String message = "";
        if (kyThi.getDsDeThi().isEmpty()) {
            kyThiRepository.deleteById(kyThiId);
            message = "Đã xóa thành công kỳ thi có id = " + kyThiId;
        } else {
            message = "Không thể xóa do kỳ thi có chứa đề thi!";
        }
        return message;
    }

    public List<KyThiDetailResponse> getAllKyThi() {
        List<KyThi> tmp = kyThiRepository.findAll();
        List<KyThiDetailResponse> list = tmp.stream().map((kiThi) -> {
            int soLuongDeMo = (int) kiThi.getDsDeThi().stream().filter(DeThi::isTrangThai).count();
            int soLuongDeDong = kiThi.getDsDeThi().size() - soLuongDeMo;
            return KyThiDetailResponse.builder()
                    .id(kiThi.getId())
                    .tenKyThi(kiThi.getTenKyThi())
                    .soLuongDeThi(kiThi.getDsDeThi().size())
                    .soLuongDeDong(soLuongDeDong)
                    .soLuongDeMo(soLuongDeMo)
                    .build();
        }).toList();
        return list;
    }

}
