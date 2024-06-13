package com.lucas.gestao_cursos.modules.instructors.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.gestao_cursos.modules.instructors.entities.InstructorEntity;

public interface InstructorRepository extends JpaRepository<InstructorEntity, UUID>{
    Optional<InstructorEntity> findByUsernameOrEmail(String username, String email);
}
