package com.example.demo.repository;

import com.example.demo.domain.SportsCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SportsCourseRepository extends JpaRepository<SportsCourse, Long> {

    List<SportsCourse> findAllByOrderByCoursePrcAsc();

    List<SportsCourse> findAllByOrderByCoursePrcDesc();

    List<SportsCourse> findAllByOrderByCourseReqstNmprCoDesc();
}
