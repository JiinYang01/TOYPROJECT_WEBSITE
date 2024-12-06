package com.example.demo.controller;

import com.example.demo.DTO.ReviewDTO;
import com.example.demo.domain.CustomUserDetails;
import com.example.demo.domain.User;
import com.example.demo.service.ReviewService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final UserService userService;
    private static final Logger log = LoggerFactory.getLogger(ReviewController.class);

    @GetMapping("/{courseId}")
    public ResponseEntity<Map<String, Object>> getReviews(@PathVariable("courseId") Long courseId) {
        // 서비스에서 리뷰 리스트 가져오기
        List<ReviewDTO> reviews = reviewService.getReviewsForCourse(courseId);

        // 평균 별점 계산
        double averageRating = reviews.stream()
                .mapToInt(ReviewDTO::getRating)
                .average()
                .orElse(0.0); // 리뷰가 없을 때 0.0 반환

        // 리뷰 수
        int reviewCount = reviews.size();

        // Map에 필요한 데이터 추가
        Map<String, Object> response = new HashMap<>();
        response.put("reviews", reviews); // 리뷰 리스트
        response.put("averageRating", averageRating); // 평균 별점
        response.put("reviewCount", reviewCount); // 리뷰 수

        // Map을 ResponseEntity로 반환
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> saveReview(@RequestBody ReviewDTO reviewDTO, @AuthenticationPrincipal CustomUserDetails user) {
        // 인증 상태 로그 추가
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authentication: {}" + authentication);
        System.out.println("User Principal: {}" + authentication.getPrincipal());
        System.out.println("Is Authenticated: {}" + authentication.isAuthenticated());

        // 현재 로그인한 사용자 정보 가져오기
        Authentication authentication2 = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication2.getName();
        User currentUser = userService.getCurrentUser(user);

        // reviewDTO에 사용자 ID 설정
        reviewDTO.setUserId(currentUser.getUserId());
        // 현재 날짜/시간 설정
        reviewDTO.setCreatedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        System.out.println(reviewDTO);

        // 서비스로 전달하여 저장
        return ResponseEntity.ok(reviewService.saveReview(reviewDTO));
    }

}
