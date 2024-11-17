package com.example.demo.repository;
import com.example.demo.domain.SurveyResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface SurveyRepository extends JpaRepository<SurveyResponse, Long> {
    Optional<SurveyResponse> findByUser_UserId(Long userId);

}
