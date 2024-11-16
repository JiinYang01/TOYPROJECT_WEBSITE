package com.example.demo.controller;

import com.example.demo.DTO.LoginForm;
import com.example.demo.DTO.SurveyForm;
import com.example.demo.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.example.demo.DTO.UserCreateForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BasicController {

    @GetMapping("/")
    public String hi(Model model) {
        model.addAttribute("title", "사이트 제목");
        model.addAttribute("brandName", "BBB");
        model.addAttribute("welcomeMessage", "사이트에 오신 것을 환영합니다");
        model.addAttribute("introMessage", "자기에게 맞는 스포츠 강좌를 알아보세요");
        model.addAttribute("customerService", "고객센터: 010-1111-0000");
        model.addAttribute("aboutDescription", "이 페이지는 웹페이지 설명입니다.");
        model.addAttribute("feature1", "스포츠 트렌드 알아보기");
        model.addAttribute("feature2", "내게 맞는 스포츠 추천");
        return "index";
    }

    @GetMapping("/surveypage")
    public String showSurveyPage(Model model) {
        model.addAttribute("formTitle", "맞춤형 스포츠 강좌");
        model.addAttribute("surveyForm", new SurveyForm());
        model.addAttribute("regions", List.of("서울", "부산", "인천"));  // Sample regions
        model.addAttribute("sports", List.of("검도", "골프", "농구", "당구", "배드민턴","복싱","볼링","야구","에어로빅","요가","유도","주짓수","댄스","롤러인라인","무용","배구","에어로빅","빙상(스케이트)","수영","스쿼시","승마","줄넘기","축구(풋살)","크로스핏","에어로빅","클라이밍","탁구","태권도","테니스","펜싱","필라테스","합기도","헬스"));  // Sample sports
        return "surveypage";
    }

    @Autowired
    private SurveyService surveyService;  // Inject SurveyService

    @PostMapping("/submitForm")
    public String submitSurveyForm(@ModelAttribute SurveyForm surveyForm, RedirectAttributes redirectAttributes) {
        surveyService.saveSurveyResponse(surveyForm);  // Use the injected service
        redirectAttributes.addFlashAttribute("message", "Survey submitted successfully!");
        return "redirect:/";
    }

}
