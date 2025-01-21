package com.alura.ForoHubAPI.dto.course;

import com.alura.ForoHubAPI.domain.model.Category;
import com.alura.ForoHubAPI.domain.model.Course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterCourseDTO(@NotBlank String name, @NotNull Category category) {

    public RegisterCourseDTO(Course course){
        this(course.getName(), course.getCategory());
    }
}
