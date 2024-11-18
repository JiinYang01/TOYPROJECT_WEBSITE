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

@RequiredArgsConstructor
@Controller
@RequestMapping("/recommend")
public class RecommendationController {
    private final RecommendationService recommendationService;

    @GetMapping("/")
    public String recommendCourses(@AuthenticationPrincipal CustomUserDetails user, Model model) {
        List<SportsCourseDTO> courses = recommendationService.getRecommendedCourses(user);

        System.out.println(courses);
        model.addAttribute("courseList", courses);
        return "course_recommend";
    }
}
