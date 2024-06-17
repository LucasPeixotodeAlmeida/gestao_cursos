package com.lucas.gestao_cursos.modules.student.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileStudentResponseDTO {
    private String username;
    private String name;
    private UUID id;
    private String email;
}
