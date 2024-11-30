package com.example.demo.service;

import com.example.demo.DTO.SportsCourseDTO;
import com.example.demo.DTO.SurveyDTO;
import com.example.demo.domain.CustomUserDetails;
import com.example.demo.domain.SportsCourse;
import com.example.demo.repository.SportsCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final SportsCourseRepository courseRepository;
    private final SurveyService surveyService;

    public List<SportsCourseDTO> getRecommendedCourses(CustomUserDetails user) {
        SurveyDTO surveyDTO = surveyService.getResponsesByUserId(user);
        List<SportsCourse> courseList = courseRepository.findRecommendedCourses(surveyDTO.getPreferredSports().get(0), surveyDTO.getPreferredSports().get(1), surveyDTO.getPreferredSports().get(2), surveyDTO.getSido(), surveyDTO.getSigugun(), surveyDTO.getPrice());

        LocalDate twoMonthsAgo = LocalDate.now().minusMonths(2);
        LocalDate today = LocalDate.now();

//        return courseList.stream()
//                .map(this::EntityToDTO) // 메서드 레퍼런스 사용
//                .collect(Collectors.toList());
//두달전으로 해서 정보가 너무 안나옴..

        System.out.println("추천 courseList: " + courseList);
        return courseList.stream()
//                .filter(sc -> {
//                    try {
//                        LocalDate beginDate = LocalDate.parse(sc.getCourseBeginDe(), DateTimeFormatter.ofPattern("yyyyMMdd"));
//                        LocalDate endDate = LocalDate.parse(sc.getCourseEndDe(), DateTimeFormatter.ofPattern("yyyyMMdd"));
//
//                        System.out.println("날짜: " + endDate);
//                        return !endDate.isBefore(twoMonthsAgo);
//                    } catch (DateTimeParseException e) {
//                        // 변환 실패 시 로그를 남기거나 예외 처리
//                        System.out.println("날짜 변환 실패: " + sc.getCourseEndDe());
//                        return false;
//                    }
//                })
                .map(this::EntityToDTO)
                .limit(3)
                .collect(Collectors.toList());
    }

    private SportsCourseDTO EntityToDTO(SportsCourse entity) {
        return new SportsCourseDTO(entity.getCourseId(), entity.getRowNum(), entity.getCrseNum(), entity.getCourseNm(), entity.getCategory(), entity.getFcltyName(), entity.getCtprvnNm(), entity.getSignguNm(),
                entity.getFcltyAddr(), entity.getFcltyDetailAddr(), entity.getTelNo(), entity.getCourseBeginDe(), entity.getCourseEndDe(), entity.getCourseReqstNmprCo(), entity.getCoursePrc());
    }





}
