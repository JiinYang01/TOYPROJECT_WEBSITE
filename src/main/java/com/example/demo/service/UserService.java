package com.example.demo.service;

import com.example.demo.DTO.UserCreateDTO;
import com.example.demo.domain.CustomUserDetails;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long register(UserCreateDTO dto) {
        User entity = dtoToEntity(dto);
        userRepository.save(entity);
        return entity.getUserId();
    }

    private User dtoToEntity(UserCreateDTO dto) {
        return new User(dto.getEmail(), passwordEncoder.encode(dto.getPassword()), dto.getUserName(), dto.getDisabled());
    }

    public User getCurrentUser(@AuthenticationPrincipal CustomUserDetails user) {

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalArgumentException("현재 로그인된 사용자 정보를 찾을 수 없습니다.");
        }

        Long userId = user.getUserId(); // 인증된 사용자의 이메일 가져오기

        System.out.println("userId: " + userId);

        return (User) userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("현재 로그인된 사용자 정보를 찾을 수 없습니다."));
    }
    
}
