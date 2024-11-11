package com.example.demo.controller;

import com.example.demo.DTO.SportsCourseDTO;
import com.example.demo.service.SportsCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/course")
public class SportsCourseController {
    private final SportsCourseService courseService;

    @GetMapping("/trend")
    public String list(@RequestParam(name = "sortType", defaultValue = "All") String sortType, Model model) {
        List<SportsCourseDTO> courseList = this.courseService.getList();

        switch(sortType) {
            case "priceAsc":
                courseList = this.courseService.getCourseSortedByPriceAsc();
                break;
            case "priceDesc":
                courseList = this.courseService.getCourseSortedByPriceDesc();
                break;
            case "popularity":
                courseList = this.courseService.getCourseSortedByCourseReqstNmprCo();
                break;
            default:
                break;
        }

        model.addAttribute("courseList", courseList);
        return "course_trend";
    }

}
