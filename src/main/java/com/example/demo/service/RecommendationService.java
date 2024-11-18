package com.example.demo.service;

import com.example.demo.DTO.SportsCourseDTO;
import com.example.demo.DTO.SurveyDTO;
import com.example.demo.domain.CustomUserDetails;
import com.example.demo.domain.SportsCourse;
import com.example.demo.repository.SportsCourseRepository;
import com.example.demo.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final SportsCourseRepository courseRepository;
    private final SurveyService surveyService;

    public List<SportsCourseDTO> getRecommendedCourses(CustomUserDetails user) {
        SurveyDTO surveyDTO = surveyService.getResponsesByUserId(user);
        List<SportsCourse> courseList = courseRepository.findRecommendedCourses(surveyDTO.getPreferredSports().get(0), surveyDTO.getPreferredSports().get(1), surveyDTO.getPreferredSports().get(2), surveyDTO.getSido(), surveyDTO.getSigugun(), surveyDTO.getPrice(), PageRequest.of(0, 3));

        return courseList.stream()
                .map(this::EntityToDTO) // 메서드 레퍼런스 사용
                .collect(Collectors.toList());
    }

    private SportsCourseDTO EntityToDTO(SportsCourse entity) {
        return new SportsCourseDTO(entity.getCourseId(), entity.getCourseNm(), entity.getCategory(), entity.getFcltyName(), entity.getCtprvnNm(), entity.getSignguNm(),
                entity.getFcltyAddr(), entity.getFcltyDetailAddr(), entity.getTelNo(), entity.getCourseBeginDe(), entity.getCourseEndDe(), entity.getCourseReqstNmprCo(), entity.getCoursePrc());
    }
}
