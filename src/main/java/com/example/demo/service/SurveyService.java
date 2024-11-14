package com.example.demo.service;

import com.example.demo.DTO.SurveyDTO;
import com.example.demo.DTO.SurveyForm;
import com.example.demo.DTO.UserCreateDTO;
import com.example.demo.domain.SurveyResponse;
import com.example.demo.domain.User;
import com.example.demo.repository.SurveyRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyService {

    @Autowired
    private final SurveyRepository surveyRepository;

    public void saveSurveyResponse(SurveyForm surveyForm) {
        SurveyResponse entity = dtoToEntity(surveyForm);
        surveyRepository.save(entity);
    }


        private SurveyResponse dtoToEntity (SurveyForm dto){
            return new SurveyResponse(dto.getDisabled(), dto.getSido(), dto.getSigugun(), dto.getGroups(), dto.getPrice(), dto.getParti(),  dto.getPreferredSports().toString());
        }
    }

