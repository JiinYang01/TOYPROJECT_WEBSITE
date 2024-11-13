package com.example.demo.entity;

import jakarta.persistence.*;

//import javax.persistence.*;

@Entity
@Table(name = "survey_responses")
public class SurveyResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String disabled;

    private String sido; // Updated from locationSido to sido
    private String sigugun; // Updated from locationSigugun to sigugun

    @Column(name = "group_preference") // Rename column to avoid SQL reserved keyword conflict
    private String groupPreference;

    private String parti;
    private int price;

    @Column(name = "preferred_sports")
    private String preferredSports;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

//    public String getDong() {
//        return dong;
//    }
//
//    public void setDong(String dong) {
//        this.dong = dong;
//    }

    public String getGroupPreference() {
        return groupPreference;
    }

    public void setGroupPreference(String groupPreference) {
        this.groupPreference = groupPreference;
    }

    public String getParti() {
        return parti;
    }

    public void setParti(String parti) {
        this.parti = parti;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPreferredSports() {
        return preferredSports;
    }

    public void setPreferredSports(String preferredSports) {
        this.preferredSports = preferredSports;
    }
}
