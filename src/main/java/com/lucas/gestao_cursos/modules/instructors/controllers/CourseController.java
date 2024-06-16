package com.lucas.gestao_cursos.modules.instructors.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.gestao_cursos.modules.instructors.dto.CreateCourseDTO;
import com.lucas.gestao_cursos.modules.instructors.entities.CourseEntity;
import com.lucas.gestao_cursos.modules.instructors.services.CreateCourseService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CreateCourseService createCourseService;
    
    @PostMapping("/")
    public CourseEntity createCourse(@Valid @RequestBody CreateCourseDTO createCourseDTO, HttpServletRequest request){
        var instructorId = request.getAttribute("instructor_id");

        var courseEntity = CourseEntity.builder()
            .active(createCourseDTO.getActive())
            .category(createCourseDTO.getCategory())
            .description(createCourseDTO.getDescription())
            .name(createCourseDTO.getName())
            .instructorId(UUID.fromString(instructorId.toString()))
            .build();


        return this.createCourseService.execute(courseEntity);
    }
}
