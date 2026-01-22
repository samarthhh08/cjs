package com.cjs.auth_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjs.auth_service.dto.response.ApiResponseDto;

@RestController("auth")
public class AuthController {

    public String hello() {
        return "Hello from Auth Service!";
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponseDto<String>> signIn() {

        // TODO: Implement sign-in logic
        // call authService.signIn(request);
        return ResponseEntity.ok(new ApiResponseDto<String>("Sign in successful", "token_abc123", true));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto<String>> signUp() {
        // TODO: Implement sign-up logic
        // call authService.signUp(request);
        return ResponseEntity.ok(new ApiResponseDto<String>("Sign Up successful", "token_abc123", true));
    }

    @PostMapping("/signout")
    public ResponseEntity<ApiResponseDto<String>> signOut() {
        // TODO: Implement sign-out logic
        // call authService.signOut(request);
        return ResponseEntity.ok(new ApiResponseDto<String>("Sign Out successful", null, true));
    }

    @PostMapping("/me")
    public ResponseEntity<ApiResponseDto<String>> me() {

        // TODO: Implement user info retrieval logic
        // call authService.getUserInfo(request);
        return ResponseEntity.ok(new ApiResponseDto<String>("User info retrieved", "user_info_xyz", true));
    }
}
