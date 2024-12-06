package com.example.demo.config;

import com.example.demo.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;  // AppConfig에서 주입된 PasswordEncoder 사용

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 설정
                //.csrf(csrf -> csrf.disable()) // 전체 비활성화 (테스트용)
                .csrf(csrf -> csrf.ignoringRequestMatchers("/reviews/**")) // 특정 요청 제외

                // URL별 접근 권한 설정
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user/login", "/user/register", "/css/**", "/js/**").permitAll() // 공개 경로
                        .requestMatchers(HttpMethod.POST, "/reviews/**").authenticated() // POST 요청은 인증 필요
                        .anyRequest().permitAll() // 나머지 요청은 모두 허용
                )

                // 로그인 설정
                .formLogin(formLogin -> formLogin
                        .loginPage("/user/login") // 커스텀 로그인 페이지
                        .defaultSuccessUrl("/", true) // 로그인 성공 시 이동 경로
                        .permitAll()
                )

                // 로그아웃 설정
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) // 로그아웃 경로
                        .logoutSuccessUrl("/user/login?logout") // 로그아웃 성공 후 이동 경로
                        .permitAll()
                );

        return http.build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
    }

    // AuthenticationManager Bean 설정
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

}
