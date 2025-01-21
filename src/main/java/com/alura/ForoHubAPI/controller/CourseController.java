package com.alura.ForoHubAPI.controller;

import com.alura.ForoHubAPI.domain.model.Course;
import com.alura.ForoHubAPI.domain.repository.CourseRepository;
import com.alura.ForoHubAPI.dto.course.CourseDTO;
import com.alura.ForoHubAPI.dto.course.RegisterCourseDTO;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/courses")
@SecurityRequirement(name = "bearer-key")
public class CourseController {

    @Autowired
    private CourseRepository repository;

    @PostMapping
    public ResponseEntity<RegisterCourseDTO> registerCourse(@Valid @RequestBody RegisterCourseDTO data , UriComponentsBuilder uriComponentsBuilder){

        Course course = repository.save(new Course(data));


        URI url = uriComponentsBuilder.path("/courses/{courseId}").buildAndExpand(course.getCourseId()).toUri();

       System.out.println(course.getCourseId());
        return ResponseEntity.created(url).body(data);
    }

    @GetMapping
    public ResponseEntity<Page<CourseDTO>> listCourses(Pageable pageable){
        return ResponseEntity.ok(repository.findAll(pageable).map(CourseDTO::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO data){
        Course course = repository.getReferenceById(data.courseId());

        course.updateCourse(data);

        repository.save(course);
        return ResponseEntity.ok(new CourseDTO(course));
    }

    @DeleteMapping("/{courseId}")
    @Transactional
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId){
        repository.deleteById(courseId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable Long courseId){
        Course course = repository.getReferenceById(courseId);
        return ResponseEntity.ok(new CourseDTO(course));
    }


}
