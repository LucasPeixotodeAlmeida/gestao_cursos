package com.lucas.gestao_cursos.modules.student.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.gestao_cursos.modules.student.entities.StudentEntity;

@RestController
@RequestMapping("/student")
public class StudentController {
    
    @PostMapping("/")
    public void createStudent(@RequestBody StudentEntity studentEntity){
        System.out.println("estudante:");
        System.out.println(studentEntity.getEmail());
    }
}
