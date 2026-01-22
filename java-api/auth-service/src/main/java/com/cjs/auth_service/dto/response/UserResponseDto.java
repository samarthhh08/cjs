package com.cjs.auth_service.dto.response;

import lombok.Data;

@Data
public class UserResponseDto {

    private String username;
    private String email;
    private int id;
    private String role;

    public UserResponseDto(String username, String email, int id, String role) {
        this.username = username;
        this.email = email;
        this.id = id;
        this.role = role;
    }
}