package com.trannam.quizz.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KyThiDetailResponse {
    private long id;
    private String tenKyThi;
    private int soLuongDeThi;
    private int soLuongDeMo;
    private int soLuongDeDong;
}
