package com.example.demo.DTO;

import java.util.List;

public class SurveyForm {

    private String disabled; // To store 장애인/비장애인 여부
    private String sido;      // To store 거주지역 - 시도
    private String sigugun;   // To store 거주지역 - 시군구
//    private String dong;      // To store 거주지역 - 동
    private String groups;    // To store 단체/개인 스포츠 선호도
    private String parti;     // To store 참여목적
    private Long price;     // To store 가격
    private String preferredSports; // To store 선호 스포츠 고르기

    // Getters and setters
    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getSido() {
        return sido;
    }

    public void setSido(String sido) {
        this.sido = sido;
    }

    public String getSigugun() {
        return sigugun;
    }

    public void setSigugun(String sigugun) {
        this.sigugun = sigugun;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getParti() {
        return parti;
    }

    public void setParti(String parti) {
        this.parti = parti;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getPreferredSports() {
        return preferredSports;
    }

    public void setPreferredSports(String preferredSports) {
        this.preferredSports = preferredSports;
    }
}
