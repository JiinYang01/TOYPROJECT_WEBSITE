package com.example.demo.DTO;

import com.example.demo.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SurveyForm {

    private String disabled; // To store 장애인/비장애인 여부
    private String sido;      // To store 거주지역 - 시도
    private String sigugun;   // To store 거주지역 - 시군구
    private String groups;    // To store 단체/개인 스포츠 선호도
    private String parti;     // To store 참여목적
    private Long price;     // To store 가격
    private List<String> preferredSports; // To store 선호 스포츠 고르기
    private User user;
}
    // Getters and setters
