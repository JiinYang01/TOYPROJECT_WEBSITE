package com.example.demo.service;

import com.example.demo.DTO.SurveyForm;
import com.example.demo.domain.SurveyResponse;
import com.example.demo.domain.User;
import com.example.demo.repository.SurveyRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyService {


    private final SurveyRepository surveyRepository;
    private final UserRepository userRepository;

    public void saveSurveyResponse(SurveyForm surveyForm) {
        // 현재 로그인된 사용자 정보 가져오기
        User currentUser = getCurrentUser();

        if (currentUser == null) {
            throw new IllegalArgumentException("현재 로그인된 사용자 정보를 찾을 수 없습니다.");
        }

        SurveyResponse entity = dtoToEntity(surveyForm,currentUser);

        surveyRepository.save(entity);
    }


        private SurveyResponse dtoToEntity (SurveyForm dto, User currentUser){
            return new SurveyResponse(dto.getDisabled(), dto.getSido(), dto.getSigugun(), dto.getGroups(), dto.getPrice(), dto.getParti(),  dto.getPreferredSports().toString(), currentUser);
        }

    // 현재 로그인된 사용자 정보를 가져오는 메서드
    private User getCurrentUser() {

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalArgumentException("현재 로그인된 사용자 정보를 찾을 수 없습니다.");
        }

        String email = authentication.getName(); // 인증된 사용자의 이메일 가져오기
        return (User) userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("현재 로그인된 사용자 정보를 찾을 수 없습니다."));
    }



    public List<SurveyResponse> getResponsesByUserId(Long userId) {
        return surveyRepository.findByUser_UserId(userId);
    }

    }

