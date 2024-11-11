package com.example.demo.service;

import com.example.demo.DTO.SportsCourseDTO;
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
    private final CategoryRepository categoryRepository;

    public List<SportsCourseDTO> getList() {
        List<SportsCourse> courseList = courseRepository.findAll();

        return courseList.stream()
                .map(this::EntityToDTO) // 메서드 레퍼런스 사용
                .collect(Collectors.toList());
    }

    private SportsCourseDTO EntityToDTO(SportsCourse entity) {
        return new SportsCourseDTO(entity.getCourseId(), entity.getCourseNm(), entity.getCategory(), entity.getFcltyName(), entity.getCtprvnNm(), entity.getCignguNm(),
                entity.getFcltyAddr(), entity.getFcltyDetailAddr(), entity.getTelNo(), entity.getCourseBeginDe(), entity.getCourseEndDe(), entity.getCourseReqstNmprCo(), entity.getCoursePrc());
    }

    public List<SportsCourseDTO> getCourseSortedByPriceAsc() {
        return courseRepository.findAllByOrderByCoursePrcAsc().stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }

    public List<SportsCourseDTO> getCourseSortedByPriceDesc() {
        return courseRepository.findAllByOrderByCoursePrcDesc().stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }

    public List<SportsCourseDTO> getCourseSortedByCourseReqstNmprCo() {
        return courseRepository.findAllByOrderByCourseReqstNmprCoDesc().stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }
}
