package com.lucas.gestao_cursos.modules.student.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.gestao_cursos.modules.student.entities.StudentEntity;
import com.lucas.gestao_cursos.modules.student.services.CreateStudentService;
import com.lucas.gestao_cursos.modules.student.services.ProfileStudentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private CreateStudentService createStudentService;

    @Autowired
    private ProfileStudentService profileStudentService;
    
    @PostMapping("/")
    public ResponseEntity<Object> createStudent(@Valid @RequestBody StudentEntity studentEntity){
        try {
           var result = this.createStudentService.execute(studentEntity);
           return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<Object> get(HttpServletRequest request){
        var idStudent = request.getAttribute("student_id");
        try {
            var profile = this.profileStudentService.execute(UUID.fromString(idStudent.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
