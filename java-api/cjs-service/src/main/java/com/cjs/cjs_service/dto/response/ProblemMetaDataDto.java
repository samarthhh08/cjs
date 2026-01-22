package com.cjs.cjs_service.dto.response;

import com.cjs.cjs_service.model.Difficulty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProblemMetaDataDto {

    private String title = "";
    private String slug = "";
    private Difficulty difficulty = Difficulty.EASY;

    // getters and setters
}
