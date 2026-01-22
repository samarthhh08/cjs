package com.cjs.cjs_service.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ApiResponseDto<T> {

    private String message;
    private T data;
    private boolean success;
    public ApiResponseDto(String message, T data, boolean success) {
        this.message = message;
        this.data = data;
        this.success = success;
    }
}
