package com.lucas.gestao_cursos.modules.instructors.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.gestao_cursos.modules.instructors.entities.CourseEntity;
import com.lucas.gestao_cursos.modules.instructors.repositories.CourseRepository;

@Service
public class CreateCourseService {


    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity execute(CourseEntity courseEntity){
        return this.courseRepository.save(courseEntity);
    }
}
