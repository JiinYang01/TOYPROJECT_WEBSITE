package com.example.demo.controller;

import com.example.demo.DTO.LoginForm;
import org.springframework.ui.Model;
import com.example.demo.DTO.UserCreateForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BasicController {

    @GetMapping("/")
    public String hi(Model model) {
        model.addAttribute("title", "사이트 제목");
        model.addAttribute("brandName", "MyBrand");
        model.addAttribute("welcomeMessage", "사이트에 오신 것을 환영합니다");
        model.addAttribute("introMessage", "자기에게 맞는 스포츠 강좌를 알아보세요");
        model.addAttribute("customerService", "고객센터: 010-1111-0000");
        model.addAttribute("aboutDescription", "이 페이지는 웹페이지 설명입니다.");
        model.addAttribute("feature1", "스포츠 트렌드 알아보기");
        model.addAttribute("feature2", "내게 맞는 스포츠 추천");
        return "index";
    }


    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginForm", new LoginForm()); // LoginForm 객체를 모델에 추가
        return "login"; // login.html 템플릿으로 이동
    }


    @GetMapping("/register")
    public String register(UserCreateForm userCreateForm) {
        return "signup";
    }
}
