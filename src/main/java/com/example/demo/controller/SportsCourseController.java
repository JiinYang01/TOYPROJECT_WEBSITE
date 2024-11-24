package com.example.demo.controller;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.DTO.DisabledSportsCourseDTO;
import com.example.demo.DTO.SportsCourseDTO;
import com.example.demo.service.CategoryService;
import com.example.demo.service.DisabledSportsCourseService;
import com.example.demo.service.SportsCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    private final DisabledSportsCourseService disabledcourseService;
    private final CategoryService categoryService;

    @GetMapping("/trend")
    public String list(@RequestParam(name = "sortType", defaultValue = "All") String sortType,
                       @RequestParam(name = "sortType1", defaultValue = "abled") String sortType1,
                       @RequestParam(name = "categoryId", required = false) Long categoryId,
                       @RequestParam(name = "ctprvn", required = false) String ctprvn,
                       @RequestParam(name = "signgu", required = false) String signgu,
                       @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "10") int size,
                       Model model) {
        Page<SportsCourseDTO> coursePage = courseService.getFilteredCourses(categoryId, sortType, ctprvn, signgu, page, size);
        List<CategoryDTO> categoryList = this.categoryService.getList();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("selectedCategoryId", categoryId);
        model.addAttribute("sortType", sortType);
        model.addAttribute("ctprvn", ctprvn);
        model.addAttribute("signgu", signgu);
        model.addAttribute("coursePage", coursePage);
        model.addAttribute("sortType1", sortType1);
        //model.addAttribute("keyword", keyword);
        return "course_trend";
    }

    @GetMapping("/disabledtrend")
    public String list1(@RequestParam(name = "sortType", defaultValue = "All") String sortType,
                        @RequestParam(name = "sortType1", defaultValue = "disabled") String sortType1,
                       @RequestParam(name = "categoryId", required = false) Long categoryId,
                       @RequestParam(name = "ctprvn", required = false) String ctprvn,
                       @RequestParam(name = "signgu", required = false) String signgu,
                       @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "10") int size,
                       Model model) {
        List<CategoryDTO> categoryList = this.categoryService.getList();
        //내코드
        Page<DisabledSportsCourseDTO> disabledcoursePage = disabledcourseService.getFilteredCourses(categoryId, sortType, ctprvn, signgu, page, size);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("selectedCategoryId", categoryId);
        model.addAttribute("sortType", sortType);
        model.addAttribute("ctprvn", ctprvn);
        model.addAttribute("signgu", signgu);
        model.addAttribute("disabledcoursePage", disabledcoursePage);
        model.addAttribute("sortType1", sortType1);
        //model.addAttribute("keyword", keyword);
        return "disabledcourse_trend";
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

    @GetMapping("/search")
    public String searchCourses(@RequestParam("keyword") String keyword,
                                @RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "size", defaultValue = "10") int size,
                                Model model) {
        Page<SportsCourseDTO> coursePage = courseService.searchCourses(keyword, page, size);
        model.addAttribute("coursePage", coursePage);
        model.addAttribute("keyword", keyword);

        return "course_trend";
    }

    @GetMapping("/disabledsearch")
    public String disabledsearchCourses(@RequestParam("keyword") String keyword,
                                @RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "size", defaultValue = "10") int size,
                                Model model) {
        Page<DisabledSportsCourseDTO> disabledcoursePage = disabledcourseService.searchCourses(keyword, page, size);
        model.addAttribute("disabledcoursePage", disabledcoursePage);
        model.addAttribute("keyword", keyword);

        return "disabledcourse_trend";
    }

    @GetMapping("/disableddetail/{courseId}")
    public String getdisabledCourseDetail(@PathVariable("courseId") Long courseId, Model model) {
        // DTO를 통해 강좌 상세 정보를 가져온다.
        DisabledSportsCourseDTO course = disabledcourseService.getCourseById(courseId);
        // 모델에 강좌 정보를 추가하여 Thymeleaf 템플릿에 전달
        model.addAttribute("course", course);
        // course_detail.html로 이동
        return "course_detail";
    }

}
