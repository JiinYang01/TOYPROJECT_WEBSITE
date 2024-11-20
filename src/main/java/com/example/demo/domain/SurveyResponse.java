package com.example.demo.domain;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

//import javax.persistence.*;

@Getter
@Entity
@Table(name = "survey_responses")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String disabled;

    @Column(name = "ctprvn_nm")
    private String sido; // Updated from locationSido to sido

    @Column(name = "signgu_nm")
    private String sigugun; // Updated from locationSigugun to sigugun

    @Column(name = "group_preference") // Rename column to avoid SQL reserved keyword conflict
    private String groupPreference;

    private String parti;

    @Column(name = "course_prc")
    private Long price;

    @Column(name = "preferred_sports")

    private String preferredSports;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 필요한 경우 다른 생성자 추가

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
