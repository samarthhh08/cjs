package com.cjs.auth_service.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cjs.auth_service.dto.request.SignInRequestDto;
import com.cjs.auth_service.dto.request.SignUpRequestDto;
import com.cjs.auth_service.dto.response.UserResponseDto;
import com.cjs.auth_service.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto signIn(SignInRequestDto request) {
        // Implement sign-in logic here
        return new UserResponseDto("username_example", "email@test.com", 1, "USER");
    }

    public UserResponseDto signUp(SignUpRequestDto request) {
        // Implement sign-up logic here
        return new UserResponseDto("username_example", "email@test.com", 1, "USER");
    }

    public UserResponseDto userInfo(String id) {
        // Implement sign-out logic here
        return new UserResponseDto("username_example", "email@test.com", 1, "USER");
    }
}
