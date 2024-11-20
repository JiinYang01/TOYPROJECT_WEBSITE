package com.example.demo.service;

import com.example.demo.DTO.SurveyDTO;
import com.example.demo.DTO.CategoryDTO;
import com.example.demo.DTO.SurveyDTO;
import com.example.demo.DTO.SurveyForm;
import com.example.demo.domain.Category;
import com.example.demo.domain.CustomUserDetails;
import com.example.demo.domain.SurveyResponse;
import com.example.demo.domain.User;
import com.example.demo.repository.SurveyRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.el.stream.Optional;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final UserRepository userRepository;

    public void saveSurveyResponse(SurveyForm surveyForm, CustomUserDetails user) {
        // 현재 로그인된 사용자 정보 가져오기
        User currentUser = getCurrentUser(user);

        if (currentUser == null) {
            throw new IllegalArgumentException("현재 로그인된 사용자 정보를 찾을 수 없습니다.");
        }

        SurveyResponse entity = dtoToEntity(surveyForm, currentUser);
        System.out.println(entity.getId());

        saveOrUpdate(entity, currentUser.getUserId());
    }


    private SurveyResponse dtoToEntity (SurveyForm dto, User currentUser){
        return new SurveyResponse(dto.getDisabled(), dto.getSido(), dto.getSigugun(), dto.getGroups(), dto.getPrice(), dto.getParti(), dto.getPreferredSports().toString(), currentUser);
    }

    // 현재 로그인된 사용자 정보를 가져오는 메서드
    private User getCurrentUser(@AuthenticationPrincipal CustomUserDetails user) {

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalArgumentException("현재 로그인된 사용자 정보를 찾을 수 없습니다.");
        }

        Long userId = user.getUserId(); // 인증된 사용자의 이메일 가져오기

        System.out.println("userId: " + userId);

        return (User) userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("현재 로그인된 사용자 정보를 찾을 수 없습니다."));
    }

    public SurveyDTO getResponsesByUserId(CustomUserDetails user) {
        User currentUser = getCurrentUser(user);

        if (currentUser == null) {
            throw new IllegalArgumentException("현재 로그인된 사용자 정보를 찾을 수 없습니다.");
        }

        SurveyResponse entity =  surveyRepository.findByUser_UserId(currentUser.getUserId()).orElseThrow(() -> new IllegalArgumentException("해당 사용자의 설문 응답을 찾을 수 없습니다."));


        List<String> preferredSports;
        String input = entity.getPreferredSports();
        if (input == null || input.isEmpty()) {
            preferredSports = List.of();
        }

        // 문자열을 분리하고 Long 타입으로 변환
        assert input != null;
        preferredSports = Arrays.stream(input.replaceAll("[\\[\\]]", "") // 대괄호 제거
                        .split(","))
                .map(String::trim)                      // 공백 제거
//                .map(Long::valueOf)                     // Long 타입으로 변환
                .collect(Collectors.toList());


        return new SurveyDTO(entity.getDisabled(),entity.getSido(),entity.getSigugun(),entity.getGroupPreference(),entity.getParti(),entity.getPrice(),preferredSports,entity.getUser().getUserId());
    }

    @Transactional
    public void saveOrUpdate(SurveyResponse entity, Long userId) {
        surveyRepository.findByUser_UserId(userId)
                    .ifPresent(existingResponse -> surveyRepository.deleteById(existingResponse.getId()));
        surveyRepository.save(entity);
    }


//내코드
public Boolean getUserId(CustomUserDetails user) {
    User currentUser = getCurrentUser(user);
    if (currentUser == null) {
        throw new IllegalArgumentException("현재 로그인된 사용자 정보를 찾을 수 없습니다.");
    }
    if(surveyRepository.findByUser_UserId(currentUser.getUserId()).isEmpty()){
     return Boolean.FALSE;
    }else{
        return Boolean.TRUE;
    }
    }
}

