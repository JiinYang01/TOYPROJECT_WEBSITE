package com.example.demo.DTO;

import com.example.demo.domain.Category;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Builder
public class SportsCourseDTO {
    private Long courseId;
    private Long rownum;
    private  Long crsenum;
    private String courseNm;      //계절별트랜드
    private Category category;
    private String fcltyName;
    private String ctprvnNm;
    private String signguNm;
    private String fcltyAddr;
    private String fcltyDetailAddr;
    private String telNo;
    private String courseBeginDe;         //계절별트랜드
    private String courseEndDe;           //계절별트랜드
    private Long courseReqstNmprCo;         //계절별트랜드
    private Long coursePrc;

    public SportsCourseDTO(Long courseId,Long rownum,Long crsenum, String courseNm, Category category, String fcltyName, String ctprvnNm, String signguNm, String fcltyAddr, String fcltyDetailAddr, String telNo, String courseBeginDe, String courseEndDe, Long courseReqstNmprCo, Long coursePrc) {
        this.courseId = courseId;
        this.crsenum=crsenum;
        this.rownum=rownum;
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
