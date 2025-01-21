package com.alura.ForoHubAPI.domain.model;

import com.alura.ForoHubAPI.dto.course.CourseDTO;
import com.alura.ForoHubAPI.dto.course.RegisterCourseDTO;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Course")
@Table(name = "courses")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "courseId")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;

    public Course(RegisterCourseDTO data) {
        this.name = data.name();
        this.category = data.category();
    }

    public void updateCourse(CourseDTO data) {
        if(data.name() != null){
            this.name = data.name();
        }
        if(data.category() != null){
            this.category = data.category();
        }
    }
}

