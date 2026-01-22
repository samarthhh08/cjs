package com.cjs.cjs_service.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAllProblemDto {

    private List<ProblemMetaDataDto> problems = new ArrayList<>();

    private long total;
    private long page;
    private int pageSize;

    // getters and setters
}
