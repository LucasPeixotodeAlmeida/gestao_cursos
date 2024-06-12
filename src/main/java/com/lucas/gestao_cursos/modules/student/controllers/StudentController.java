package com.lucas.gestao_cursos.modules.student.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.gestao_cursos.modules.student.entities.StudentEntity;
import com.lucas.gestao_cursos.modules.student.repositories.StudentRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    
    @PostMapping("/")
    public StudentEntity createStudent(@Valid @RequestBody StudentEntity studentEntity){
        return studentRepository.save(studentEntity);
    }
}
