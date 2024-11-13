package com.example.demo.service;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.DTO.SportsCourseDTO;
import com.example.demo.domain.Category;
import com.example.demo.domain.SportsCourse;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.SportsCourseRepository;
import lombok.RequiredArgsConstructor;
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

    public List<SportsCourseDTO> getFilteredCourses(Long categoryId, String sortType, String ctprvn, String signgu) {
        System.out.println(signgu);

        List<SportsCourse> courses;

        if (categoryId != null && ctprvn == null) {
            if (sortType.equals("priceAsc"))
                courses = courseRepository.findByCategory_CategoryIdOrderByCoursePrcAsc(categoryId);
            else if (sortType.equals("priceDesc"))
                courses = courseRepository.findByCategory_CategoryIdOrderByCoursePrcDesc(categoryId);
            else if (sortType.equals("popularity"))
                courses = courseRepository.findByCategory_CategoryIdOrderByCourseReqstNmprCoDesc(categoryId);
            else
                courses = courseRepository.findByCategory_CategoryId(categoryId);
        } else if (categoryId == null && ctprvn == null){
            if (sortType.equals("priceAsc"))
                courses = courseRepository.findAllByOrderByCoursePrcAsc();
            else if (sortType.equals("priceDesc"))
                courses = courseRepository.findAllByOrderByCoursePrcDesc();
            else if (sortType.equals("popularity"))
                courses = courseRepository.findAllByOrderByCourseReqstNmprCoDesc();
            else
                courses = courseRepository.findAll();
        } else if (categoryId != null) {
            if (signgu == null || signgu.isEmpty()) {
                if (sortType.equals("priceAsc"))
                    courses = courseRepository.findByCategory_CategoryIdAndCtprvnNmOrderByCoursePrcAsc(categoryId, ctprvn);
                else if (sortType.equals("priceDesc"))
                    courses = courseRepository.findByCategory_CategoryIdAndCtprvnNmOrderByCoursePrcDesc(categoryId, ctprvn);
                else if (sortType.equals("popularity"))
                    courses = courseRepository.findByCategory_CategoryIdAndCtprvnNmOrderByCourseReqstNmprCoDesc(categoryId, ctprvn);
                else
                    courses = courseRepository.findByCategory_CategoryIdAndCtprvnNm(categoryId, ctprvn);
            } else {
                if (sortType.equals("priceAsc"))
                    courses = courseRepository.findByCategory_CategoryIdAndCtprvnNmAndSignguNmOrderByCoursePrcAsc(categoryId, ctprvn, signgu);
                else if (sortType.equals("priceDesc"))
                    courses = courseRepository.findByCategory_CategoryIdAndCtprvnNmAndSignguNmOrderByCoursePrcDesc(categoryId, ctprvn, signgu);
                else if (sortType.equals("popularity"))
                    courses = courseRepository.findByCategory_CategoryIdAndCtprvnNmAndSignguNmOrderByCourseReqstNmprCoDesc(categoryId, ctprvn, signgu);
                else
                    courses = courseRepository.findByCategory_CategoryIdAndCtprvnNmAndSignguNm(categoryId, ctprvn, signgu);
            }
        } else {
            if (signgu == null || signgu.isEmpty()) {
                if (sortType.equals("priceAsc"))
                    courses = courseRepository.findByCtprvnNmOrderByCoursePrcAsc(ctprvn);
                else if (sortType.equals("priceDesc"))
                    courses = courseRepository.findByCtprvnNmOrderByCoursePrcDesc(ctprvn);
                else if (sortType.equals("popularity"))
                    courses = courseRepository.findByCtprvnNmOrderByCourseReqstNmprCoDesc(ctprvn);
                else
                    courses = courseRepository.findByCtprvnNm(ctprvn);
            } else {
                if (sortType.equals("priceAsc"))
                    courses = courseRepository.findByCtprvnNmAndSignguNmOrderByCoursePrcAsc(ctprvn, signgu);
                else if (sortType.equals("priceDesc"))
                    courses = courseRepository.findByCtprvnNmAndSignguNmOrderByCoursePrcDesc(ctprvn, signgu);
                else if (sortType.equals("popularity"))
                    courses = courseRepository.findByCtprvnNmAndSignguNmOrderByCourseReqstNmprCoDesc(ctprvn, signgu);
                else
                    courses = courseRepository.findByCtprvnNmAndSignguNm(ctprvn, signgu);
            }
        }

        return courses.stream().map(this::EntityToDTO).collect(Collectors.toList());
    }

    public SportsCourseDTO getCourseById(Long courseId) {
        SportsCourse sportsCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course ID: " + courseId));

        return EntityToDTO(sportsCourse);
    }
}
