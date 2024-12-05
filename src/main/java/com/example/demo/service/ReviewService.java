package com.example.demo.service;

import com.example.demo.DTO.ReviewDTO;
import com.example.demo.domain.Review;
import com.example.demo.domain.SportsCourse;
import com.example.demo.domain.User;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.SportsCourseRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final SportsCourseRepository courseRepository;

    public List<ReviewDTO> getReviewsForCourse(Long sportsCourseId) {
        return reviewRepository.findBySportsCourse_CourseId(sportsCourseId).stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }

    public ReviewDTO saveReview(ReviewDTO reviewDTO) {
        Review review = dtoToEntity(reviewDTO);
        Review savedReview = reviewRepository.save(review);
        return reviewDTO;
    }

    private ReviewDTO EntityToDTO(Review entity) {
        return new ReviewDTO(entity.getId(), entity.getUser().getUserId(), entity.getSportsCourse().getCourseId(), entity.getContent(), entity.getRating(), entity.getCreatedDate());
    }

    private Review dtoToEntity(ReviewDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + dto.getUserId()));

        SportsCourse sportsCourse = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("SportsCourse not found with ID: " + dto.getCourseId()));

        return new Review(user, sportsCourse, dto.getContent(), dto.getRating(), dto.getCreatedDate());
    }
}
