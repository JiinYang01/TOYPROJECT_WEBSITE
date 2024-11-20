package com.example.demo.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
public class SurveyDTO {
    private String disabled; // To store 장애인/비장애인 여부
    private String sido;      // To store 거주지역 - 시도
    private String sigugun;   // To store 거주지역 - 시군구
    private String groups;    // To store 단체/개인 스포츠 선호도
    private String parti;     // To store 참여목적
    private Long price;     // To store 가격
    private List<String> preferredSports; // To store 선호 스포츠 고르기
    private Long userId;      // 사용자 ID 추가

    public SurveyDTO(String disabled, String sido, String sigugun, String groups, String parti, Long price, List<String> preferredSports,Long userId) {
        this.disabled = disabled;
        this.sido = sido;
        this.sigugun = sigugun;
        this.groups = groups;
        this.parti = parti;
        this.price = price;
        this.preferredSports = preferredSports;
        this.userId = userId;
    }


}
