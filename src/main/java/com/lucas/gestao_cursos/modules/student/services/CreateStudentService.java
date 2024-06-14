package com.lucas.gestao_cursos.modules.student.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lucas.gestao_cursos.exceptions.UserFoundException;
import com.lucas.gestao_cursos.modules.student.entities.StudentEntity;
import com.lucas.gestao_cursos.modules.student.repositories.StudentRepository;

@Service
public class CreateStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public StudentEntity execute(StudentEntity studentEntity){
        this.studentRepository.findByUsernameOrEmail(studentEntity.getUsername(), studentEntity.getEmail())
        .ifPresent((user) ->{
            throw new UserFoundException();
        });
        var password = passwordEncoder.encode(studentEntity.getPassword());
        studentEntity.setPassword(password);
        return studentRepository.save(studentEntity);
    }
    
}
