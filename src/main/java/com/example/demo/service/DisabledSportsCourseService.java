package com.example.demo.service;

import com.example.demo.DTO.DisabledSportsCourseDTO;
import com.example.demo.domain.disabledSportsCourse;
import com.example.demo.repository.DisabledSportsCourseRepository;
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
public class DisabledSportsCourseService {

    private final DisabledSportsCourseRepository courseRepository;
    public List<DisabledSportsCourseDTO> getList() {
        List<disabledSportsCourse> courseList = courseRepository.findAll();
        return courseList.stream().map(this::entityToDTO).collect(Collectors.toList()); }
    private DisabledSportsCourseDTO entityToDTO(disabledSportsCourse entity) {
        return new DisabledSportsCourseDTO(entity.getCourseId(), entity.getRowNum(), entity.getCrseNum(), entity.getCourseNm(), entity.getCategory(), entity.getFcltyName(), entity.getCtprvnNm(), entity.getSignguNm(),
                entity.getFcltyAddr(), entity.getFcltyDetailAddr(), entity.getTelNo(), entity.getCourseBeginDe(), entity.getCourseEndDe(), entity.getCourseReqstNmprCo(), entity.getCoursePrc());
    }

    public DisabledSportsCourseDTO getCourseById(Long courseId)
    {
        disabledSportsCourse sportsCourse = courseRepository.findById(courseId).orElseThrow(() ->
            new IllegalArgumentException("Invalid course ID: " + courseId)); return entityToDTO(sportsCourse); }
    public Page<DisabledSportsCourseDTO> getFilteredCourses(Long categoryId, String sortType, String ctprvn, String signgu, int page, int size) { Pageable pageable = PageRequest.of(page, size, Sort.by("courseNm").ascending()); Page<disabledSportsCourse> coursesPage = null; if (categoryId != null && ctprvn == null) { switch (sortType)
    { case "priceAsc" -> coursesPage = courseRepository.findByCategory_CategoryIdOrderByCoursePrcAsc(categoryId, pageable);
        case "priceDesc" -> coursesPage = courseRepository.findByCategory_CategoryIdOrderByCoursePrcDesc(categoryId, pageable);
        case "popularity" -> coursesPage = courseRepository.findByCategory_CategoryIdOrderByCrseNumDesc(categoryId, pageable);
        default -> coursesPage = courseRepository.findByCategory_CategoryId(categoryId, pageable); } }
    else if (categoryId == null && ctprvn == null)
        { switch (sortType)
        { case "priceAsc" -> coursesPage = courseRepository.findByRowNumOrderByCoursePrcAsc(1L, pageable);
            case "priceDesc" -> coursesPage = courseRepository.findByRowNumOrderByCoursePrcDesc(1L, pageable);
            case "popularity" -> coursesPage = courseRepository.findByRowNumOrderByCrseNumDesc(1L, pageable);
            default -> coursesPage = courseRepository.findByRowNum(1L, pageable); } }
    else if (categoryId != null) { if (signgu == null || signgu.isEmpty()) {
        switch (sortType)
    { case "priceAsc" -> coursesPage = courseRepository.findByCategory_CategoryIdAndCtprvnNmOrderByCoursePrcAsc(categoryId, ctprvn, pageable);
        case "priceDesc" -> coursesPage = courseRepository.findByCategory_CategoryIdAndCtprvnNmOrderByCoursePrcDesc(categoryId, ctprvn, pageable);
        case "popularity" -> coursesPage = courseRepository.findByCategory_CategoryIdAndCtprvnNmOrderByCrseNumDesc(categoryId, ctprvn, pageable);
        default -> coursesPage = courseRepository.findByCategory_CategoryIdAndCtprvnNm(categoryId, ctprvn, pageable); } }
    else { switch (sortType)
    { case "priceAsc" -> coursesPage = courseRepository.findByCategory_CategoryIdAndCtprvnNmAndSignguNmOrderByCoursePrcAsc(categoryId, ctprvn, signgu, pageable);
        case "priceDesc" -> coursesPage = courseRepository.findByCategory_CategoryIdAndCtprvnNmAndSignguNmOrderByCoursePrcDesc(categoryId, ctprvn, signgu, pageable);
        case "popularity" -> coursesPage = courseRepository.findByCategory_CategoryIdAndCtprvnNmAndSignguNmOrderByCrseNumDesc(categoryId, ctprvn, signgu, pageable);
        default -> coursesPage = courseRepository.findByCategory_CategoryIdAndCtprvnNmAndSignguNm(categoryId, ctprvn, signgu, pageable); } } }
    else { if
    (signgu == null || signgu.isEmpty())
    { switch (sortType)
    { case "priceAsc" -> coursesPage = courseRepository.findByCtprvnNmOrderByCoursePrcAsc(ctprvn, pageable);
        case "priceDesc" -> coursesPage = courseRepository.findByCtprvnNmOrderByCoursePrcDesc(ctprvn, pageable);
        case "popularity" -> coursesPage = courseRepository.findByCtprvnNmOrderByCrseNumDesc(ctprvn, pageable);
        default -> coursesPage = courseRepository.findByCtprvnNm(ctprvn, pageable); } }
    else { switch (sortType) {
        case "priceAsc" -> coursesPage = courseRepository.findByCtprvnNmAndSignguNmOrderByCoursePrcAsc(ctprvn, signgu, pageable);
        case "priceDesc" -> coursesPage = courseRepository.findByCtprvnNmAndSignguNmOrderByCoursePrcDesc(ctprvn, signgu, pageable);
        case "popularity" -> coursesPage = courseRepository.findByCtprvnNmAndSignguNmOrderByCrseNumDesc(ctprvn, signgu, pageable);
        default -> coursesPage = courseRepository.findByCtprvnNmAndSignguNm(ctprvn, signgu, pageable); } } }
    if (coursesPage == null) return Page.empty(pageable);
    else return coursesPage.map(this::entityToDTO); }
    public Page<DisabledSportsCourseDTO> searchCourses(String keyword, int page, int size)
    { Pageable pageable = PageRequest.of(page, size, Sort.by("courseNm").ascending());
        Page<disabledSportsCourse> coursePage = courseRepository.findByCourseNmContainingIgnoreCase(keyword, pageable);
        if (coursePage == null) return Page.empty(pageable); else return coursePage.map(this::entityToDTO); }
    public List<DisabledSportsCourseDTO> getFilteredData(String ctprvn, String signgu) {
        if ("전체".equals(ctprvn) && "전체".equals(signgu)) {
            return courseRepository.findAll().stream()
                    .map(this::entityToDTO) // 메서드 레퍼런스 사용
                    .collect(Collectors.toList());
        } else if ("전체".equals(signgu)) {
            return courseRepository.findByCtprvnNm(ctprvn).stream()
                    .map(this::entityToDTO)
                    .collect(Collectors.toList());
        } else {
            return courseRepository.findByCtprvnNmAndSignguNm(ctprvn, signgu).stream()
                    .map(this::entityToDTO)
                    .collect(Collectors.toList());
        }
    }

}