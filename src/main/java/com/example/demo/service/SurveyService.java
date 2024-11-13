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
//        private final UserRepository userRepository;
        SurveyResponse entity = dtoToEntity(surveyForm);
        SurveyRepository.save(entity);
    }

//        SurveyResponse response = new SurveyResponse();
//
//        response.setDisabled(surveyForm.getDisabled());
//        response.setSido(surveyForm.getSido());  // Map sido
//        response.setSigugun(surveyForm.getSigugun());  // Map sigugun
//        response.setGroupPreference(surveyForm.getGroups());
//        response.setParti(surveyForm.getParti());
//
//        // Parse and map price
//        if (surveyForm.getPrice() != null && !surveyForm.getPrice().isEmpty()) {
//            response.setPrice(Integer.parseInt(surveyForm.getPrice()));
//        } else {
//            response.setPrice(0);
//        }
//
//        if (surveyForm.getPreferredSports() != null) {
//            response.setPreferredSports(String.join(",", surveyForm.getPreferredSports()));
//        } else {
//            response.setPreferredSports("");
//        }
//
//        surveyRepository.save(response);
//    }

        private SurveyResponse dtoToEntity (SurveyForm dto){
            return new SurveyResponse(dto.getDisabled(), dto.getSido(), dto.getSigugun(), dto.getGroups(), dto.getPrice(), dto.getParti(),  dto.getPreferredSports());
        }
    }

