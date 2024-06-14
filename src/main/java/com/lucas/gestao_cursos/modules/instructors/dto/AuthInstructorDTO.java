package com.lucas.gestao_cursos.modules.instructors.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthInstructorDTO {
    private String username;
    private String password;
}
