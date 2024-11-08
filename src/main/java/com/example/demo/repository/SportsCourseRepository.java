package com.example.demo.repository;

import com.example.demo.domain.SportsCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportsCourseRepository extends JpaRepository<SportsCourse, Long> {
}
