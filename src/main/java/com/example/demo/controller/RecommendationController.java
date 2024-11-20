package com.example.demo.controller;

import com.example.demo.DTO.SportsCourseDTO;
import com.example.demo.DTO.SurveyDTO;
import com.example.demo.domain.CustomUserDetails;
import com.example.demo.service.RecommendationService;
import com.example.demo.service.SportsCourseService;
import com.example.demo.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("/recommend")
public class RecommendationController {
    private final RecommendationService recommendationService;
    private final SurveyService surveyService;

    @GetMapping("/")
    public String recommendCourses(@AuthenticationPrincipal CustomUserDetails user, Model model) {
        List<SportsCourseDTO> courses = recommendationService.getRecommendedCourses(user);

        SurveyDTO response = surveyService.getResponsesByUserId(user);
        model.addAttribute("responses", response);

//        List<String> categoryList = courses.stream()
//                .map(course -> course.getCategory().getCategoryName())
//                .distinct()
//                .collect(Collectors.toList());
//        model.addAttribute("categoryList", categoryList);

        // 카테고리별로 코스를 그룹화
        Map<String, List<SportsCourseDTO>> groupedCourses = courses.stream()
                .collect(Collectors.groupingBy(course -> course.getCategory().getCategoryName()));

        // 모델에 그룹화된 데이터를 추가
        model.addAttribute("groupedCourses", groupedCourses);

        return "course_recommend";
    }
}
