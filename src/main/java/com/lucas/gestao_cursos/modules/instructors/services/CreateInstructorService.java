package com.lucas.gestao_cursos.modules.instructors.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lucas.gestao_cursos.exceptions.UserFoundException;
import com.lucas.gestao_cursos.modules.instructors.entities.InstructorEntity;
import com.lucas.gestao_cursos.modules.instructors.repositories.InstructorRepository;

@Service
public class CreateInstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public InstructorEntity execute(InstructorEntity instructorEntity){
        this.instructorRepository.findByUsernameOrEmail(instructorEntity.getUsername(), instructorEntity.getEmail())
        .ifPresent((user) ->{
            throw new UserFoundException();
        });
        var password = passwordEncoder.encode(instructorEntity.getPassword());
        instructorEntity.setPassword(password);
                
        return this.instructorRepository.save(instructorEntity);
    }
}
