package com.lucas.gestao_cursos.modules.instructors.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lucas.gestao_cursos.modules.instructors.dto.AuthInstructorDTO;
import com.lucas.gestao_cursos.modules.instructors.repositories.InstructorRepository;

@Service
public class AuthInstructorService {
    @Autowired
    private InstructorRepository instructorRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void execute(AuthInstructorDTO authInstructorDTO){
        var instructor = this.instructorRepository.findByUsername(authInstructorDTO.getUsername()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Instrutor não encontrado");
        });

        passwordEncoder.matches(authInstructorDTO.getPassword(), instructor.getPassword());


    }
}
