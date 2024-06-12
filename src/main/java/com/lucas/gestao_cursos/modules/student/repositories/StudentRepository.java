package com.lucas.gestao_cursos.modules.student.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.gestao_cursos.modules.student.entities.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID>{
    Optional<StudentEntity> findByUsernameOrEmail(String username, String email);
}
