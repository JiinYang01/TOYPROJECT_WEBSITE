package com.example.demo.DTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Builder
public class ReviewDTO {
    private Long id;
    private Long userId;
    private Long courseId;
    private String content;
    private int rating; // 1~5점 평가
    private String createdDate;

    public ReviewDTO(Long id, Long userId, Long sportsCourseId, String content, int rating, String createdDate) {
        this.id = id;
        this.userId = userId;
        this.courseId = sportsCourseId;
        this.content = content;
        this.rating = rating;
        this.createdDate = createdDate;
    }
}
