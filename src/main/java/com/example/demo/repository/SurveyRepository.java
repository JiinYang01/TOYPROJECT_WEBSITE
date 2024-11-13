package com.example.demo.repository;

import com.example.demo.entity.SurveyResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<SurveyResponse, Long> {
}
