package com.lucas.gestao_cursos.modules.instructors.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthInstructorResponseDTO {
    private String access_token;
    private Long expires_in;
}
