package com.example.demo.controller;

import com.example.demo.DTO.*;
import com.example.demo.DTO.LoginForm;
import com.example.demo.DTO.SurveyDTO;
import com.example.demo.DTO.SurveyDTO;
import com.example.demo.DTO.SurveyForm;
import com.example.demo.domain.CustomUserDetails;
import com.example.demo.domain.SurveyResponse;
import com.example.demo.service.CategoryService;
import com.example.demo.service.SurveyService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BasicController {
    private final CategoryService categoryService;

    @GetMapping("/")
    public String hi(Model model,@AuthenticationPrincipal CustomUserDetails user) {

        model.addAttribute("title", "사이트 제목");
       // model.addAttribute("brandName", "BBB");
        model.addAttribute("welcomeMessage", "BBB 사이트에 오신 것을 환영합니다");
        model.addAttribute("introMessage", "자기에게 맞는 스포츠 강좌를 알아보세요");
        model.addAttribute("aboutDescription", "※스포츠강좌를 추천해주는 서비스입니다. 회원가입을 통해 나에게 맞는 강좌를 찾아보세요※");
        model.addAttribute("feature1", "스포츠 트렌드 알아보기");
        model.addAttribute("feature2", "내게 맞는 스포츠 추천");
        model.addAttribute("feature3","종목별 인기종목 도표로 보기");
        model.addAttribute("feature4","계절별 인기종목 도표로 보기");
        if (user!=null){
        if(surveyService.getUserId(user)){
            SurveyDTO  response = surveyService.getResponsesByUserId(user);
            model.addAttribute("havesurvey","수정");
            model.addAttribute("responses", response);
        }}
        return "index";
    }

    @GetMapping("/surveypage")
    public String showSurveyPage(Model model, @AuthenticationPrincipal CustomUserDetails user) {
        model.addAttribute("formTitle", "맞춤형 스포츠 강좌");
        model.addAttribute("surveyForm", new SurveyForm());
        model.addAttribute("regions", List.of("서울", "부산", "인천"));  // Sample regions

        List<CategoryDTO> sports = categoryService.getList();
        model.addAttribute("sports", sports);  // Sample sports
        if(!surveyService.getUserId(user)){
            return "surveypage";}
        else{
            return "redirect:/recommend/";
        }
    }

    @GetMapping("/update")
    public String update(Model model) {
        model.addAttribute("formTitle", "맞춤형 스포츠 강좌");
        model. addAttribute("surveyForm", new SurveyForm());
        model.addAttribute("regions", List.of("서울", "부산", "인천"));  // Sample regions

        List<CategoryDTO> sports = categoryService.getList();
        model.addAttribute("sports", sports);  // Sample sports
        return "surveypage";

    }

    @Autowired
    private SurveyService surveyService;  // Inject SurveyService

    @PostMapping("/submitForm")
    public String submitSurveyForm(@ModelAttribute SurveyForm surveyForm, @AuthenticationPrincipal CustomUserDetails user, RedirectAttributes redirectAttributes, Model model) {
        surveyService.saveSurveyResponse(surveyForm,user);
//        SurveyDTO response = surveyService.getResponsesByUserId(user);
//        model.addAttribute("responses", response);
//        System.out.println(model);
        return "redirect:/recommend/";
    }
    @GetMapping("/explain")
    public String explain( ) {

        return "/explain";
    }


}
