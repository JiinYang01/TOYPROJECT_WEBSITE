package com.example.demo.controller;

import com.example.demo.DTO.ReviewDTO;
import com.example.demo.domain.CustomUserDetails;
import com.example.demo.domain.User;
import com.example.demo.service.ReviewService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final UserService userService;

    @GetMapping("/{courseId}")
    public ResponseEntity<List<ReviewDTO>> getReviews(@PathVariable("courseId") Long courseId) {
        long startTime = System.currentTimeMillis();
        List<ReviewDTO> reviews = reviewService.getReviewsForCourse(courseId);
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + "ms");
        System.out.println(reviews);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> saveReview(@RequestBody ReviewDTO reviewDTO, @AuthenticationPrincipal CustomUserDetails user) {
        // 현재 로그인한 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
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
