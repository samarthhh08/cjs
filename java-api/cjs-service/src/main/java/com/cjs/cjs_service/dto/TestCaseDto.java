package com.cjs.cjs_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TestCaseDto {

    @NotBlank
    private String input = "";

    @NotBlank
    private String output = "";

    private boolean isSample = false;

    // getters and setters
}
