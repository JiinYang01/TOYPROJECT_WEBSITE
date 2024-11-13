package com.example.demo.service;

import com.example.demo.DTO.SurveyForm;
import com.example.demo.entity.SurveyResponse;
import com.example.demo.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    public void saveSurveyResponse(SurveyForm surveyForm) {
        SurveyResponse response = new SurveyResponse();

        response.setDisabled(surveyForm.getDisabled());
        response.setSido(surveyForm.getSido());  // Map sido
        response.setSigugun(surveyForm.getSigugun());  // Map sigugun
        response.setGroupPreference(surveyForm.getGroups());
        response.setParti(surveyForm.getParti());

        // Parse and map price
        if (surveyForm.getPrice() != null && !surveyForm.getPrice().isEmpty()) {
            response.setPrice(Integer.parseInt(surveyForm.getPrice()));
        } else {
            response.setPrice(0);
        }

        if (surveyForm.getPreferredSports() != null) {
            response.setPreferredSports(String.join(",", surveyForm.getPreferredSports()));
        } else {
            response.setPreferredSports("");
        }

        surveyRepository.save(response);
    }
}
