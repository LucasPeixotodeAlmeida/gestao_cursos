package com.lucas.gestao_cursos.modules.instructors.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.gestao_cursos.modules.instructors.entities.CourseEntity;
import com.lucas.gestao_cursos.modules.instructors.services.CreateCourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CreateCourseService createCourseService;
    
    @PostMapping("/")
    public CourseEntity createCourse(@Valid @RequestBody CourseEntity courseEntity){
           return this.createCourseService.execute(courseEntity);
    }
}
