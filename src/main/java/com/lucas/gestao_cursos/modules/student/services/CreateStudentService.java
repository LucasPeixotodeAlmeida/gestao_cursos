package com.lucas.gestao_cursos.modules.student.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.gestao_cursos.exceptions.UserFoundException;
import com.lucas.gestao_cursos.modules.student.entities.StudentEntity;
import com.lucas.gestao_cursos.modules.student.repositories.StudentRepository;

@Service
public class CreateStudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentEntity execute(StudentEntity studentEntity){
        this.studentRepository.findByUsernameOrEmail(studentEntity.getUsername(), studentEntity.getEmail())
        .ifPresent((user) ->{
            throw new UserFoundException();
        });
        return studentRepository.save(studentEntity);
    }
    
}
