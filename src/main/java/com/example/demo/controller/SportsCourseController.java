package com.example.demo.controller;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.DTO.SportsCourseDTO;
import com.example.demo.service.CategoryService;
import com.example.demo.service.SportsCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/course")
public class SportsCourseController {
    private final SportsCourseService courseService;
    private final CategoryService categoryService;

    @GetMapping("/trend")
    public String list(@RequestParam(name = "sortType", defaultValue = "All") String sortType,
                       @RequestParam(name = "categoryId", required = false) Long categoryId,
                       @RequestParam(name = "ctprvn", required = false) String ctprvn,
                       @RequestParam(name = "signgu", required = false) String signgu, Model model) {
        List<SportsCourseDTO> courseList = this.courseService.getFilteredCourses(categoryId, sortType, ctprvn, signgu);
        List<CategoryDTO> categoryList = this.categoryService.getList();

        model.addAttribute("courseList", courseList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("selectedCategoryId", categoryId);
        model.addAttribute("sortType", sortType);
        model.addAttribute("ctprvn", ctprvn);
        model.addAttribute("signgu", signgu);

        return "course_trend";
    }

    @GetMapping("/detail/{courseId}")
    public String getCourseDetail(@PathVariable("courseId") Long courseId, Model model) {
        // DTO를 통해 강좌 상세 정보를 가져온다.
        SportsCourseDTO course = courseService.getCourseById(courseId);

        // 모델에 강좌 정보를 추가하여 Thymeleaf 템플릿에 전달
        model.addAttribute("course", course);

        // course_detail.html로 이동
        return "course_detail";
    }

}
