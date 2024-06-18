package com.lucas.gestao_cursos.modules.instructors.controllers;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.gestao_cursos.modules.instructors.dto.AuthInstructorDTO;
import com.lucas.gestao_cursos.modules.instructors.services.AuthInstructorService;

@RestController
@RequestMapping("/instructor")
public class AuthInstructorController {
    
    @Autowired
    private AuthInstructorService authInstructorService;

    @PostMapping("/auth")
    public ResponseEntity<Object> create(@RequestBody AuthInstructorDTO authInstructorDTO) throws AuthenticationException{
        try {
            var result = this.authInstructorService.execute(authInstructorDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
