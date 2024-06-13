package com.lucas.gestao_cursos.modules.instructors.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.gestao_cursos.modules.instructors.entities.InstructorEntity;
import com.lucas.gestao_cursos.modules.instructors.services.CreateInstructorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/instructor")
public class InstructorController {
    @Autowired
    private CreateInstructorService createInstructorService;
    
    @PostMapping("/")
    public ResponseEntity<Object> createStudent(@Valid @RequestBody InstructorEntity instructorEntity){
        try {
           var result = this.createInstructorService.execute(instructorEntity);
           return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
