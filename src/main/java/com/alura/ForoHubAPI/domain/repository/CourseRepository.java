package com.alura.ForoHubAPI.domain.repository;


import com.alura.ForoHubAPI.domain.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
