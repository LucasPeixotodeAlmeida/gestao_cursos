package com.lucas.gestao_cursos.modules.instructors.dto;

import com.lucas.gestao_cursos.enums.ActiveCourseEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class CreateCourseDTO {
    private String name;
    private String category;
    @Enumerated(EnumType.STRING)
    private ActiveCourseEnum active; 
    private String description;
}
