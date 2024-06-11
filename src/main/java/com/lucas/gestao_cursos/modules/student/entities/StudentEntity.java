package com.lucas.gestao_cursos.modules.student.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "students")
public class StudentEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @Pattern(regexp = "^(?!\\s*$).+", message = "O username não deve conter")
    private String username;

    @Email(message = "O e-mail deve ser válido")
    private String email;

    @Length(min = 8, max = 120)
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
