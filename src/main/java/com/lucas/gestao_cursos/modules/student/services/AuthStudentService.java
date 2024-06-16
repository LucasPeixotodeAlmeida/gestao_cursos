package com.lucas.gestao_cursos.modules.student.services;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lucas.gestao_cursos.modules.student.dto.AuthStudentRequestDTO;
import com.lucas.gestao_cursos.modules.student.dto.AuthStudentResponseDTO;
import com.lucas.gestao_cursos.modules.student.repositories.StudentRepository;

@Service
public class AuthStudentService {

    @Value("${security.token.secret.student}")
    private String secretKey;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public AuthStudentResponseDTO execute(AuthStudentRequestDTO authStudentRequestDTO) throws AuthenticationException{
        var student = this.studentRepository.findByUsername(authStudentRequestDTO.username()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Username/password incorretos");
        });

        var passwordMatches = this.passwordEncoder.matches(authStudentRequestDTO.password(), student.getPassword());
        if(!passwordMatches){
            throw new AuthenticationException();
        }
        
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var token = JWT.create().withIssuer("umedy")
            .withExpiresAt(Instant.now().plus(Duration.ofHours(1)))
            .withClaim("roles", Arrays.asList("student"))
            .withSubject(student.getId().toString())
            .sign(algorithm);

        var authCandidateResponse = AuthStudentResponseDTO.builder()
            .access_token(token)
            .build();

        return authCandidateResponse;
    }
}
