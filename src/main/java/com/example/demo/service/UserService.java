package com.example.demo.service;

import com.example.demo.DTO.UserCreateDTO;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

    
}
