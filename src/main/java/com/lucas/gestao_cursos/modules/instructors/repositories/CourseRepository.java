package com.lucas.gestao_cursos.modules.instructors.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.gestao_cursos.modules.instructors.entities.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID>{
    
}
