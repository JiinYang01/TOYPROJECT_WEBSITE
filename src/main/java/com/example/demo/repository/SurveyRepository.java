package com.example.demo.repository;

import com.example.demo.domain.CustomUserDetails;
import com.example.demo.domain.SurveyResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurveyRepository extends JpaRepository<SurveyResponse, Long> {
    Optional<SurveyResponse> findByUser_UserId(Long userId);
}
