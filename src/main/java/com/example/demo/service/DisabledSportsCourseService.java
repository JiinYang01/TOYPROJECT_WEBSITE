package com.example.demo.service;

import com.example.demo.DTO.DisabledSportsCourseDTO;
import com.example.demo.DTO.SportsCourseDTO;
import com.example.demo.domain.SportsCourse;
import com.example.demo.domain.disabledSportsCourse;
import com.example.demo.repository.DisabledSportsCourseRepository;
import com.example.demo.repository.SportsCourseRepository;
import com.example.demo.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final DisabledSportsCourseRepository courseRepository;

    public List<DisabledSportsCourseDTO> getList() {
        List<disabledSportsCourse> courseList = courseRepository.findAll();

        return courseList.stream()
                .map(this::EntityToDTO) // 메서드 레퍼런스 사용
                .collect(Collectors.toList());
    }

    private DisabledSportsCourseDTO EntityToDTO(disabledSportsCourse entity) {
        return new DisabledSportsCourseDTO(entity.getCourseId(), entity.getCourseNm(), entity.getCategory(), entity.getFcltyName(), entity.getCtprvnNm(), entity.getSignguNm(),
                entity.getFcltyAddr(), entity.getFcltyDetailAddr(), entity.getTelNo(), entity.getCourseBeginDe(), entity.getCourseEndDe(), entity.getCourseReqstNmprCo(), entity.getCoursePrc(),entity.getTrobltyNM());
    }

    public DisabledSportsCourseDTO getCourseById(Long courseId) {
        disabledSportsCourse sportsCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course ID: " + courseId));
        return EntityToDTO(sportsCourse);
    }






}
