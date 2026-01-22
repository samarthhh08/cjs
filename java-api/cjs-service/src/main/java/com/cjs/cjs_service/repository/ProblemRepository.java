package com.cjs.cjs_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cjs.cjs_service.model.Problem;

public interface ProblemRepository extends JpaRepository<Problem, Integer> {

    public Optional<Problem> findBySlug(String slug);
}
