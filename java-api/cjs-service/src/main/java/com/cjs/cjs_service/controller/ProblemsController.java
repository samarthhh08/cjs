package com.cjs.cjs_service.controller;

import com.cjs.cjs_service.dto.*;
import com.cjs.cjs_service.dto.request.CreateProblemDto;
import com.cjs.cjs_service.dto.request.UpdateProblemDto;
import com.cjs.cjs_service.dto.response.ApiResponseDto;
import com.cjs.cjs_service.dto.response.GetAllProblemDto;
import com.cjs.cjs_service.dto.response.GetProblemDto;
import com.cjs.cjs_service.dto.response.ProblemMetaDataDto;
import com.cjs.cjs_service.dto.response.SampleTestCaseDto;
import com.cjs.cjs_service.dto.response.AdminGetProblemDto;
import com.cjs.cjs_service.service.ProblemService;

import jakarta.validation.Valid;
import com.cjs.cjs_service.model.ProblemTag;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/problems")
public class ProblemsController {

        private final ProblemService problemService;

        public ProblemsController(ProblemService problemService) {
                this.problemService = problemService;
        }

        // ===============================
        // GET ALL PROBLEMS (PUBLIC)
        // ===============================
        @GetMapping
        public ResponseEntity<ApiResponseDto<GetAllProblemDto>> getAllProblems(
                        @RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "10") int pageSize) {

                if (page <= 0)
                        page = 1;
                if (pageSize <= 0 || pageSize > 50)
                        pageSize = 10;

                Pageable pageable = PageRequest.of(page - 1, pageSize);

                var problemsPage = problemService.getProblems(
                                null, // difficulty
                                null, // tags
                                pageable);

                GetAllProblemDto responseDto = new GetAllProblemDto();
                responseDto.setProblems(
                                problemsPage.getContent().stream()
                                                .map(p -> {
                                                        ProblemMetaDataDto dto = new ProblemMetaDataDto();
                                                        dto.setTitle(p.getTitle());
                                                        dto.setSlug(p.getSlug());
                                                        dto.setDifficulty(p.getDifficulty());
                                                        return dto;
                                                })
                                                .collect(Collectors.toList()));
                responseDto.setTotal(problemsPage.getTotalElements());
                responseDto.setPage(page);
                responseDto.setPageSize(pageSize);

                return ResponseEntity.ok(
                                new ApiResponseDto<>(
                                                "Problems fetched successfully",
                                                responseDto,
                                                true));
        }

        // ===============================
        // GET PROBLEM BY SLUG (PUBLIC)
        // ===============================
        @GetMapping("/{slug}")
        public ResponseEntity<ApiResponseDto<GetProblemDto>> getProblem(
                        @PathVariable String slug) {

                var problem = problemService.getProblemBySlug(slug);

                if (problem == null) {
                        return ResponseEntity.status(404).body(
                                        new ApiResponseDto<>(
                                                        "Problem not found",
                                                        null,
                                                        false));
                }

                GetProblemDto dto = new GetProblemDto();
                dto.setId(problem.getId());
                dto.setTitle(problem.getTitle());
                dto.setDescription(problem.getDescription());
                dto.setDifficulty(problem.getDifficulty());

                // dto.setTags(
                //                 problem.getProblemTags()
                //                                 .stream()
                //                                 .map(pt -> pt.getTag().getName())
                //                                 .collect(Collectors.toList()));

                dto.setSampleTestCases(
                                problem.getTestCases()
                                                .stream()
                                                .filter(tc -> tc.isSample())
                                                .map(tc -> {
                                                        SampleTestCaseDto sc = new SampleTestCaseDto();
                                                        sc.setInput(tc.getInput());
                                                        sc.setOutput(tc.getExpectedOutput());
                                                        return sc;
                                                })
                                                .collect(Collectors.toList()));

                return ResponseEntity.ok(
                                new ApiResponseDto<>(
                                                "Problem fetched successfully",
                                                dto,
                                                true));
        }

        // ===============================
        // GET PROBLEM (ADMIN)
        // ===============================
        @PreAuthorize("hasRole('ADMIN')")
        @GetMapping("/admin/{slug}")
        public ResponseEntity<ApiResponseDto<AdminGetProblemDto>> getProblemAdmin(
                        @PathVariable String slug) {

                var problem = problemService.getProblemBySlug(slug);

                if (problem == null) {
                        return ResponseEntity.status(404).body(
                                        new ApiResponseDto<>(
                                                        "Problem not found",
                                                        null,
                                                        false));
                }

                AdminGetProblemDto dto = new AdminGetProblemDto();
                dto.setId(problem.getId());
                dto.setTitle(problem.getTitle());
                dto.setDescription(problem.getDescription());
                dto.setDifficulty(problem.getDifficulty());

                // ✅ TAGS
                // List<String> tags = problem.getProblemTags()
                //                 .stream()
                //                 .map(pt -> pt.getTag().getName())
                //                 .collect(Collectors.toList());
                // dto.setTags(tags);

                // ✅ TEST CASES
                dto.setTestCases(
                                problem.getTestCases()
                                                .stream()
                                                .map(tc -> {
                                                        TestCaseDto tcd = new TestCaseDto();
                                                        tcd.setInput(tc.getInput());
                                                        tcd.setOutput(tc.getExpectedOutput());
                                                        tcd.setSample(tc.isSample()); // ✅ FIXED
                                                        return tcd;
                                                })
                                                .collect(Collectors.toList()));

                return ResponseEntity.ok(
                                new ApiResponseDto<>(
                                                "Problem fetched successfully",
                                                dto,
                                                true));

        }

        // ===============================
        // CREATE PROBLEM (ADMIN)
        // ===============================
        @PreAuthorize("hasRole('ADMIN')")
        @PostMapping
        public ResponseEntity<ApiResponseDto<Integer>> createProblem(
                        @Valid @RequestBody CreateProblemDto dto) {

                int problemId = problemService.createProblem(dto);

                return ResponseEntity.ok(
                                new ApiResponseDto<>(
                                                "Problem created successfully",
                                                problemId,
                                                true));
        }

        // ===============================
        // UPDATE PROBLEM (ADMIN)
        // ===============================
        @PreAuthorize("hasRole('ADMIN')")
        @PutMapping("/{id}")
        public ResponseEntity<ApiResponseDto<Boolean>> updateProblem(
                        @PathVariable int id,
                        @Valid @RequestBody UpdateProblemDto dto) {

                boolean updated = problemService.updateProblem(id, dto);

                if (!updated) {
                        return ResponseEntity.status(404).body(
                                        new ApiResponseDto<>(
                                                        "Problem not found",
                                                        false,
                                                        false));
                }

                return ResponseEntity.ok(
                                new ApiResponseDto<>(
                                                "Problem updated successfully",
                                                true,
                                                true));
        }
}
