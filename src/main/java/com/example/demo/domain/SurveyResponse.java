package com.example.demo.domain;

import jakarta.persistence.*;

import java.util.List;

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
    private Long price;

    @Column(name = "preferred_sports")

    private String preferredSports;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public SurveyResponse(String disabled, String sido, String sigugun, String groupPreference, Long price, String parti, String preferredSports,User user) {
        this.disabled = disabled;
        this.sido = sido;
        this.sigugun = sigugun;
        this.groupPreference = groupPreference;
        this.price = price;
        this.parti = parti;
        this.preferredSports = preferredSports;
        this.user = user;
    }
}
