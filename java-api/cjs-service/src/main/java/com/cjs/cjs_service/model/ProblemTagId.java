package com.cjs.cjs_service.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class ProblemTagId implements Serializable {

    private int problemId;
    private int tagId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProblemTagId that = (ProblemTagId) o;
        return problemId == that.problemId &&
               tagId == that.tagId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(problemId, tagId);
    }
}
