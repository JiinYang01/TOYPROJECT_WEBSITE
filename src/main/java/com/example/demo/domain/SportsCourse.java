package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SportsCourse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String courseNm;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    private String fcltyName;
    private String ctprvnNm;
    private String cignguNm;
    private String fcltyAddr;
    private String fcltyDetailAddr;
    private String telNo;
    private String courseBeginDe;
    private String courseEndDe;
    private Long courseReqstNmprCo;
    private Long coursePrc;

    public SportsCourse(Long courseId, String courseNm, Category category, String fcltyName, String ctprvnNm, String cignguNm, String fcltyAddr, String fcltyDetailAddr, String telNo, String courseBeginDe, String courseEndDe, Long courseReqstNmprCo, Long coursePrc) {
        this.courseId = courseId;
        this.courseNm = courseNm;
        this.category = category;
        this.fcltyName = fcltyName;
        this.ctprvnNm = ctprvnNm;
        this.cignguNm = cignguNm;
        this.fcltyAddr = fcltyAddr;
        this.fcltyDetailAddr = fcltyDetailAddr;
        this.telNo = telNo;
        this.courseBeginDe = courseBeginDe;
        this.courseEndDe = courseEndDe;
        this.courseReqstNmprCo = courseReqstNmprCo;
        this.coursePrc = coursePrc;
    }
}
