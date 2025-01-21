package com.alura.ForoHubAPI.dto.course;

import com.alura.ForoHubAPI.domain.model.Category;
import com.alura.ForoHubAPI.domain.model.Course;

import jakarta.validation.constraints.NotBlank;

public record CourseDTO(@NotBlank Long courseId, String name, Category category) {
    
    public CourseDTO(Course course){
        this(course.getCourseId(), course.getName(), course.getCategory());
    }
}
