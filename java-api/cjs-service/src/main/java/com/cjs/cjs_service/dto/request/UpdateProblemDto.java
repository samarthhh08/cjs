package com.cjs.cjs_service.dto.request;
import com.cjs.cjs_service.dto.TestCaseDto;
import com.cjs.cjs_service.model.Difficulty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UpdateProblemDto {

    @NotBlank
    @Size(max = 150)
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private Difficulty difficulty;

    @Min(100)
    @Max(10000)
    private int timeLimitMs;

    @Min(64)
    @Max(1024)
    private int memoryLimitMb;

    private boolean isPublished;

    private List<String> tags = new ArrayList<>();

    private List<TestCaseDto> testCases = new ArrayList<>();

    // getters and setters
}
