package com.example.demo.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeasonalCourseDataDTO {
    private String categoryName;
    private String year;
    private String season;
    private Long totalRequestCount;
}
