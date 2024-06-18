package com.lucas.gestao_cursos.modules.student.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.gestao_cursos.modules.student.dto.AuthStudentRequestDTO;
import com.lucas.gestao_cursos.modules.student.services.AuthStudentService;

@RestController
@RequestMapping("/student")
public class AuthStudentController {

    @Autowired
    private AuthStudentService authStudentService;

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthStudentRequestDTO authStudentRequestDTO){
        try {
            var token = this.authStudentService.execute(authStudentRequestDTO);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
