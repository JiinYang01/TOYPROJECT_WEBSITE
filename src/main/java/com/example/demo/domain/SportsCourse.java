package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SportsCourse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private Long rownum;
    private Long crsenum;
    private String courseNm;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
    private String fcltyName;
    private String ctprvnNm;
    private String signguNm;
    private String fcltyAddr;
    private String fcltyDetailAddr;
    private String telNo;
    private String courseBeginDe;
    private String courseEndDe;
    private Long courseReqstNmprCo;
    private Long coursePrc;

    @Transient // DB에 없는 필드로 쿼리 결과에서만 사용
    private Long row_num;
    @Transient // DB에 없는 필드로 쿼리 결과에서만 사용
    private Long totalReqstNmprCo;

    public SportsCourse(Long courseId,Long rownum,Long crsenum,String courseNm, Category category, String fcltyName, String ctprvnNm, String signguNm, String fcltyAddr, String fcltyDetailAddr, String telNo, String courseBeginDe, String courseEndDe, Long courseReqstNmprCo, Long coursePrc) {
        this.courseId = courseId;
        this.rownum=rownum;
        this.crsenum=crsenum;
        this.courseNm = courseNm;
        this.category = category;
        this.fcltyName = fcltyName;
        this.ctprvnNm = ctprvnNm;
        this.signguNm = signguNm;
        this.fcltyAddr = fcltyAddr;
        this.fcltyDetailAddr = fcltyDetailAddr;
        this.telNo = telNo;
        this.courseBeginDe = courseBeginDe;
        this.courseEndDe = courseEndDe;
        this.courseReqstNmprCo = courseReqstNmprCo;
        this.coursePrc = coursePrc;
    }


}
