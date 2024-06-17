package com.lucas.gestao_cursos.modules.student.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lucas.gestao_cursos.modules.student.dto.ProfileStudentResponseDTO;
import com.lucas.gestao_cursos.modules.student.repositories.StudentRepository;

@Service
public class ProfileStudentService {

    @Autowired
    private StudentRepository studentRepository;
    
    public ProfileStudentResponseDTO execute(UUID idStudent){
        var student = this.studentRepository.findById(idStudent).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found");
        });

        var StudentDTO = ProfileStudentResponseDTO.builder()
            .username(student.getUsername())
            .name(student.getName())
            .email(student.getEmail())
            .id(student.getId())
            .build();

        return StudentDTO;
    }
}
