package com.example.demo.controller;

import com.example.demo.DTO.LoginForm;
import com.example.demo.DTO.UserCreateDTO;
import com.example.demo.DTO.UserCreateForm;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String register(UserCreateForm userCreateForm) {
        return "signup";
    }

    @PostMapping("/register")
    public String register(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
            return "signup";
        }

        UserCreateDTO userCreateDTO = new UserCreateDTO(userCreateForm.getEmail(), userCreateForm.getPassword1(), userCreateForm.getUserName(), userCreateForm.getDisabled());

        try {
            userService.register(userCreateDTO);
        } catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("registerFailed", "이미 등록된 사용자입니다.");
            return "signup";
        } catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("registerFailed", e.getMessage());
            return "signup";
        }

        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginForm", new LoginForm()); // LoginForm 객체를 모델에 추가
        return "login"; // login.html 템플릿으로 이동
    }


//    @GetMapping("/register")
//    public String register(UserCreateForm userCreateForm) {
//        return "signup";
//    }
}
