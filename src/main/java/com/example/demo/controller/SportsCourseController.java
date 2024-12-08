package com.example.demo.controller;
import com.example.demo.DTO.CategoryDTO;
import com.example.demo.DTO.DisabledSportsCourseDTO;
import com.example.demo.DTO.SeasonalCourseDataDTO;
import com.example.demo.DTO.SportsCourseDTO;
import com.example.demo.service.CategoryService;
import com.example.demo.service.DisabledSportsCourseService;
import com.example.demo.service.SportsCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Controller
@RequestMapping("/course")
public class SportsCourseController {
    private final SportsCourseService courseService;
    private final DisabledSportsCourseService disabledcourseService;
    private final CategoryService categoryService;

//    @GetMapping("/trend")
//    public String list(@RequestParam(name = "sortType", defaultValue = "All") String sortType,
//                       @RequestParam(name = "sortType1", defaultValue = "abled") String sortType1,
//                       @RequestParam(name = "categoryId", required = false) Long categoryId,
//                       @RequestParam(name = "ctprvn", required = false) String ctprvn,
//                       @RequestParam(name = "signgu", required = false) String signgu,
//                       @RequestParam(value = "page", defaultValue = "0") int page,
//                       @RequestParam(value = "size", defaultValue = "12") int size,
//                       Model model) {
//        Page<SportsCourseDTO> coursePage = courseService.getFilteredCourses(categoryId, sortType, ctprvn, signgu, page, size);
//        List<CategoryDTO> categoryList = this.categoryService.getList();
//        model.addAttribute("categoryList", categoryList);
//        model.addAttribute("selectedCategoryId", categoryId);
//        model.addAttribute("sortType", sortType);
//        model.addAttribute("ctprvn", ctprvn);
//        model.addAttribute("signgu", signgu);
//        model.addAttribute("coursePage", coursePage);
//        model.addAttribute("sortType1", sortType1);
//        //model.addAttribute("keyword", keyword);
//        return "course_trend";
//    }

    @GetMapping("/trend")
    public String list(@RequestParam(name = "sortType", defaultValue = "All") String sortType,
                       @RequestParam(name = "sortType1", defaultValue = "abled") String sortType1,
                       @RequestParam(name = "categoryId", required = false) Long categoryId,
                       @RequestParam(name = "crsenum", required = false) Long crsenum,
                       @RequestParam(name = "ctprvn", required = false) String ctprvn,
                       @RequestParam(name = "signgu", required = false) String signgu,
                       @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "12") int size,
                       Model model) {
        // 필터링된 코스 데이터 가져오기
        Page<SportsCourseDTO> coursePage = courseService.getFilteredCourses(categoryId, sortType, ctprvn, signgu, page, size);
        List<CategoryDTO> categoryList = this.categoryService.getList();

        // Pagination 및 필터링 URL 생성
        String currentUrl = generateUrl(sortType, sortType1, categoryId, ctprvn, signgu, page, size);
        String nextPageUrl = page < coursePage.getTotalPages() - 1
                ? generateUrl(sortType, sortType1, categoryId, ctprvn, signgu, page + 1, size)
                : null;
        String prevPageUrl = page > 0
                ? generateUrl(sortType, sortType1, categoryId, ctprvn, signgu, page - 1, size)
                : null;

        // Model에 필요한 데이터 추가
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("selectedCategoryId", categoryId);
        model.addAttribute("sortType", sortType);
        model.addAttribute("sortType1", sortType1);
        model.addAttribute("ctprvn", ctprvn);
        model.addAttribute("signgu", signgu);
        model.addAttribute("coursePage", coursePage);
        model.addAttribute("currentUrl", currentUrl);
        model.addAttribute("nextPageUrl", nextPageUrl);
        model.addAttribute("prevPageUrl", prevPageUrl);
        model.addAttribute("feature3","종목별 인기종목 도표로 보기");
        model.addAttribute("feature4","계절별 인기종목 도표로 보기");
        //model.addAttribute("keyword", keyword);
        return "course_trend";
    }

    // URL 생성 메서드
    private String generateUrl(String sortType, String sortType1, Long categoryId, String ctprvn, String signgu, int page, int size) {
        return UriComponentsBuilder.fromPath("/course/trend")
                .queryParam("sortType", sortType)
                .queryParam("sortType1", sortType1)
                .queryParamIfPresent("categoryId", Optional.ofNullable(categoryId))
                .queryParamIfPresent("ctprvn", Optional.ofNullable(ctprvn))
                .queryParamIfPresent("signgu", Optional.ofNullable(signgu))
                .queryParam("page", page)
                .queryParam("size", size)
                .toUriString();
    }

    private String generateUrl1(String sortType, String sortType1, Long categoryId, String ctprvn, String signgu, int page, int size) {
        return UriComponentsBuilder.fromPath("/course/disabledtrend")
                .queryParam("sortType1", sortType)
                .queryParam("sortType", sortType1)
                .queryParamIfPresent("categoryId", Optional.ofNullable(categoryId))
                .queryParamIfPresent("ctprvn", Optional.ofNullable(ctprvn))
                .queryParamIfPresent("signgu", Optional.ofNullable(signgu))
                .queryParam("page", page)
                .queryParam("size", size)
                .toUriString();
    }





    @GetMapping("/disabledtrend")
    public String list1(@RequestParam(name = "sortType", defaultValue = "All") String sortType,
                        @RequestParam(name = "sortType1", defaultValue = "disabled") String sortType1,
                       @RequestParam(name = "categoryId", required = false) Long categoryId,
                       @RequestParam(name = "ctprvn", required = false) String ctprvn,
                       @RequestParam(name = "signgu", required = false) String signgu,
                       @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "12") int size,
                       Model model) {
        {
            Page<DisabledSportsCourseDTO> disabledcoursePage = disabledcourseService.getFilteredCourses(categoryId, sortType, ctprvn, signgu, page, size);
            List<CategoryDTO> categoryList = categoryService.getList();
            String currentUrl = generateUrl1(sortType1, sortType, categoryId, ctprvn, signgu, page, size);
            String nextPageUrl = page < disabledcoursePage.getTotalPages() - 1 ? generateUrl1(sortType1, sortType, categoryId, ctprvn, signgu, page + 1, size) : null;
            String prevPageUrl = page > 0 ? generateUrl1(sortType1, sortType, categoryId, ctprvn, signgu, page - 1, size) : null;
            model.addAttribute("categoryList", categoryList); model.addAttribute("selectedCategoryId", categoryId);
            model.addAttribute("sortType", sortType);
            model.addAttribute("sortType1", sortType1);
            model.addAttribute("ctprvn", ctprvn);
            model.addAttribute("signgu", signgu);
            model.addAttribute("disabledcoursePage", disabledcoursePage);
            model.addAttribute("currentUrl", currentUrl);
            model.addAttribute("nextPageUrl", nextPageUrl);
            model.addAttribute("prevPageUrl", prevPageUrl);
            model.addAttribute("feature3","종목별 인기종목 도표로 보기");
            model.addAttribute("feature4","계절별 인기종목 도표로 보기");
            return "disabledcourse_trend";}
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
        List<CategoryDTO> categoryList = this.categoryService.getList();
        Page<SportsCourseDTO> coursePage = courseService.searchCourses(keyword, page, size);
        model.addAttribute("coursePage", coursePage);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("feature3","종목별 인기종목 도표로 보기");
        model.addAttribute("feature4","계절별 인기종목 도표로 보기");
        return "course_trend";
    }

    @GetMapping("/disabledsearch")
    public String disabledsearchCourses(@RequestParam("keyword") String keyword,
                                @RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "size", defaultValue = "10") int size,
                                Model model) {
        List<CategoryDTO> categoryList = this.categoryService.getList();
        Page<DisabledSportsCourseDTO> disabledcoursePage = disabledcourseService.searchCourses(keyword, page, size);
        model.addAttribute("disabledcoursePage", disabledcoursePage);
        model.addAttribute("keyword", keyword);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("feature3","종목별 인기종목 도표로 보기");
        model.addAttribute("feature4","계절별 인기종목 도표로 보기");
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

    @ResponseBody
    @GetMapping("/region/api/chart-data")
    public Map<String, Object> getChartData(
            @RequestParam(name="ctprvn", required = false, defaultValue = "전체") String ctprvn,
            @RequestParam(name="signgu", required = false, defaultValue = "전체") String signgu) {

        // 필터링된 데이터 가져오기
        List<SportsCourseDTO> filteredData = courseService.getFilteredData(ctprvn, signgu);

        // 데이터를 카테고리별로 그룹화하고, 값을 합산
        Map<String, Long> groupedData = filteredData.stream()
                .collect(Collectors.groupingBy(
                        course -> course.getCategory().getCategoryName(), // 카테고리별 그룹화
                        Collectors.summingLong(SportsCourseDTO::getCourseReqstNmprCo) // 값을 합산
                ));

        // 그룹화된 데이터를 labels와 values 리스트로 분리
        List<String> labels = new ArrayList<>(groupedData.keySet());
        List<Long> values = new ArrayList<>(groupedData.values());

        // 응답 데이터 구성
        Map<String, Object> response = new HashMap<>();
        response.put("labels", labels);
        response.put("values", values);
        return response;
    }

    @GetMapping("/region/dashboard")
    public String showDashboard(Model model) {
        // 예제 데이터: 실제로는 데이터베이스나 서비스에서 데이터를 가져옵니다.
        List<CategoryDTO> categoryList = this.categoryService.getList();

        // Model에 데이터 추가
        model.addAttribute("categories", categoryList);

        // Thymeleaf 템플릿 파일 이름 (resources/templates/sports-dashboard.html)
        return "region_dashboard";
    }



//    @GetMapping("/seasonal")
//    public String getSeasonalDashboardByYear(Model model) {
//        List<SeasonalCourseDataDTO> seasonalData = courseService.getSeasonalCourseDataByYear();
//
//        // 년도 목록 생성 (null 값 제거)
//        List<String> years = seasonalData.stream()
//                .map(SeasonalCourseDataDTO::getYear)
//                .filter(Objects::nonNull) // null 값 제거
//                .distinct()
//                .sorted()
//                .collect(Collectors.toList());
//
//        // 모델에 데이터 추가
////        System.out.println("Seasonal Data Size: " + seasonalData.size());
//
////        System.out.println("Seasonal Data: " + seasonalData);
//        System.out.println("Model Seasonal Data: " + model.getAttribute("seasonalData"));
//
////        model.addAttribute("seasonalData", seasonalData);
//        model.addAttribute("years", years);
//
//        return "seasons_dashboard";
//    }


    @GetMapping("/seasonal")
    public String getSeasonalDashboardByYear(Model model) {

        List<SeasonalCourseDataDTO> seasonalData = courseService.getSeasonalCourseDataByYear();

        if (seasonalData == null || seasonalData.isEmpty()) {
            System.out.println("No data found for seasonalData.");
        }

        List<String> years = seasonalData.stream()
                .map(SeasonalCourseDataDTO::getYear)
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .collect(Collectors.toList());


        model.addAttribute("seasonalData", seasonalData);
        model.addAttribute("years", years);

        return "seasons_dashboard";
    }




}
