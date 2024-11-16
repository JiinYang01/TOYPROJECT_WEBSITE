package com.example.demo.service;

import com.example.demo.domain.CustomUserDetails;
import com.example.demo.domain.User; // 사용자 정의 User 클래스
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 데이터베이스에서 사용자 검색
        User user = (User) userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));

        // 권한 설정 - roles가 없는 경우 기본 권한 설정
        String[] roles = user.getRoles() != null ?
                user.getRoles().toArray(new String[0]) : new String[]{"USER"};

        // org.springframework.security.core.userdetails.User를 명시적으로 사용
//        return org.springframework.security.core.userdetails.User.builder()
//                .username(user.getEmail())
//                .password(user.getPassword())
//                .roles(user.getRoles().toArray(new String[0])) // 권한 설정
//                .build();

        return new CustomUserDetails(user.getUserName(), user.getPassword(), user.getUserId(), List.of());
    }



}
