package com.lucas.gestao_cursos.modules.student.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.gestao_cursos.modules.student.entities.StudentEntity;
import com.lucas.gestao_cursos.modules.student.services.CreateStudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private CreateStudentService createStudentService;
    
    @PostMapping("/")
    public ResponseEntity<Object> createStudent(@Valid @RequestBody StudentEntity studentEntity){
        try {
           var result = this.createStudentService.execute(studentEntity);
           return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
