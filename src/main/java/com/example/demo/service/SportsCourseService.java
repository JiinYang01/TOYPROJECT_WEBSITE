package com.example.demo.service;

import com.example.demo.DTO.SportsCourseDTO;
import com.example.demo.domain.SportsCourse;
import com.example.demo.repository.SportsCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SportsCourseService {
    private final SportsCourseRepository courseRepository;

    public List<SportsCourseDTO> getList() {
        List<SportsCourse> courseList = courseRepository.findAll();

        return courseList.stream()
                .map(this::EntityToDTO) // 메서드 레퍼런스 사용
                .collect(Collectors.toList());
    }

    private SportsCourseDTO EntityToDTO(SportsCourse entity) {
        return new SportsCourseDTO(entity.getCourseId(), entity.getCourseNm(), entity.getCategory(), entity.getFcltyName(), entity.getCtprvnNm(), entity.getSignguNm(),
                entity.getFcltyAddr(), entity.getFcltyDetailAddr(), entity.getTelNo(), entity.getCourseBeginDe(), entity.getCourseEndDe(), entity.getCourseReqstNmprCo(), entity.getCoursePrc());
    }

    public Page<SportsCourseDTO> getFilteredCourses(Long categoryId, String sortType, String ctprvn, String signgu, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("courseNm").ascending());
        Page<SportsCourse> coursesPage = null;

        System.out.println(signgu);

        if (categoryId != null && ctprvn == null) {
            switch (sortType) {
                case "priceAsc" ->
                        coursesPage = courseRepository.findByCategory_CategoryIdOrderByCoursePrcAsc(categoryId, pageable);
                case "priceDesc" ->
                        coursesPage = courseRepository.findByCategory_CategoryIdOrderByCoursePrcDesc(categoryId, pageable);
                case "popularity" ->
                        coursesPage = courseRepository.findByCategory_CategoryIdOrderByCourseReqstNmprCoDesc(categoryId, pageable);
                default -> coursesPage = courseRepository.findByCategory_CategoryId(categoryId, pageable);
            }
        } else if (categoryId == null && ctprvn == null){
            switch (sortType) {
                case "priceAsc" -> coursesPage = courseRepository.findAllByOrderByCoursePrcAsc(pageable);
                case "priceDesc" -> coursesPage = courseRepository.findAllByOrderByCoursePrcDesc(pageable);
                case "popularity" -> coursesPage = courseRepository.findAllByOrderByCourseReqstNmprCoDesc(pageable);
                default -> coursesPage = courseRepository.findAll(pageable);
            }
        } else if (categoryId != null) {
            if (signgu == null || signgu.isEmpty()) {
                switch (sortType) {
                    case "priceAsc" ->
                            coursesPage = courseRepository.findByCategory_CategoryIdAndCtprvnNmOrderByCoursePrcAsc(categoryId, ctprvn, pageable);
                    case "priceDesc" ->
                            coursesPage = courseRepository.findByCategory_CategoryIdAndCtprvnNmOrderByCoursePrcDesc(categoryId, ctprvn, pageable);
                    case "popularity" ->
                            coursesPage = courseRepository.findByCategory_CategoryIdAndCtprvnNmOrderByCourseReqstNmprCoDesc(categoryId, ctprvn, pageable);
                    default ->
                            coursesPage = courseRepository.findByCategory_CategoryIdAndCtprvnNm(categoryId, ctprvn, pageable);
                }
            } else {
                switch (sortType) {
                    case "priceAsc" ->
                            coursesPage = courseRepository.findByCategory_CategoryIdAndCtprvnNmAndSignguNmOrderByCoursePrcAsc(categoryId, ctprvn, signgu, pageable);
                    case "priceDesc" ->
                            coursesPage = courseRepository.findByCategory_CategoryIdAndCtprvnNmAndSignguNmOrderByCoursePrcDesc(categoryId, ctprvn, signgu, pageable);
                    case "popularity" ->
                            coursesPage = courseRepository.findByCategory_CategoryIdAndCtprvnNmAndSignguNmOrderByCourseReqstNmprCoDesc(categoryId, ctprvn, signgu, pageable);
                    default ->
                            coursesPage = courseRepository.findByCategory_CategoryIdAndCtprvnNmAndSignguNm(categoryId, ctprvn, signgu, pageable);
                }
            }
        } else {
            if (signgu == null || signgu.isEmpty()) {
                switch (sortType) {
                    case "priceAsc" ->
                            coursesPage = courseRepository.findByCtprvnNmOrderByCoursePrcAsc(ctprvn, pageable);
                    case "priceDesc" ->
                            coursesPage = courseRepository.findByCtprvnNmOrderByCoursePrcDesc(ctprvn, pageable);
                    case "popularity" ->
                            coursesPage = courseRepository.findByCtprvnNmOrderByCourseReqstNmprCoDesc(ctprvn, pageable);
                    default -> coursesPage = courseRepository.findByCtprvnNm(ctprvn, pageable);
                }
            } else {
                switch (sortType) {
                    case "priceAsc" ->
                            coursesPage = courseRepository.findByCtprvnNmAndSignguNmOrderByCoursePrcAsc(ctprvn, signgu, pageable);
                    case "priceDesc" ->
                            coursesPage = courseRepository.findByCtprvnNmAndSignguNmOrderByCoursePrcDesc(ctprvn, signgu, pageable);
                    case "popularity" ->
                            coursesPage = courseRepository.findByCtprvnNmAndSignguNmOrderByCourseReqstNmprCoDesc(ctprvn, signgu, pageable);
                    default -> coursesPage = courseRepository.findByCtprvnNmAndSignguNm(ctprvn, signgu, pageable);
                }
            }
        }

        if (coursesPage == null)
            return Page.empty(pageable);
        else
            return coursesPage.map(this::EntityToDTO);
    }

    public SportsCourseDTO getCourseById(Long courseId) {
        SportsCourse sportsCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course ID: " + courseId));

        return EntityToDTO(sportsCourse);
    }

//    public List<SportsCourseDTO> searchCourses(String keyword) {
//        List<SportsCourse> courseList = courseRepository.findByCourseNmContainingIgnoreCase(keyword);
//
//        return courseList.stream()
//                .map(this::EntityToDTO) // 메서드 레퍼런스 사용
//                .collect(Collectors.toList());
//    }

    public Page<SportsCourseDTO> searchCourses(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("courseNm").ascending());
        Page<SportsCourse> coursePage = courseRepository.findByCourseNmContainingIgnoreCase(keyword, pageable);

        if (coursePage == null)
            return Page.empty(pageable);
        else
            return coursePage.map(this::EntityToDTO);
    }




}
