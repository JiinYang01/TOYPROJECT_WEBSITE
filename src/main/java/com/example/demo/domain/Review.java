package com.example.demo.domain;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private SportsCourse sportsCourse;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false)
    private int rating; // 1~5점 평가
    private String createdDate = LocalDateTime.now().toString();

    public Review(User user, SportsCourse sportsCourse, String content, int rating, String createdDate) {
        this.user = user;
        this.sportsCourse = sportsCourse;
        this.content = content;
        this.rating = rating;
        this.createdDate = createdDate;
    }
}
