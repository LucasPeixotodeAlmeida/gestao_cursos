package com.lucas.gestao_cursos.modules.instructors.services;

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
import com.lucas.gestao_cursos.modules.instructors.dto.AuthInstructorDTO;
import com.lucas.gestao_cursos.modules.instructors.dto.AuthInstructorResponseDTO;
import com.lucas.gestao_cursos.modules.instructors.repositories.InstructorRepository;

@Service
public class AuthInstructorService {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private InstructorRepository instructorRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthInstructorResponseDTO execute(AuthInstructorDTO authInstructorDTO) throws AuthenticationException{
        var instructor = this.instructorRepository.findByUsername(authInstructorDTO.getUsername()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Username/password incorretos");
        });

        var passwordMatches = passwordEncoder.matches(authInstructorDTO.getPassword(), instructor.getPassword());

        if(!passwordMatches){
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expires_in = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create().withIssuer("umedy")
            .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
            .withSubject(instructor.getId().toString())
            .withExpiresAt(expires_in)
            .withClaim("roles", Arrays.asList("INSTRUCTOR"))
            .sign(algorithm);

        var authInstructorResponseDTO = AuthInstructorResponseDTO.builder()
            .access_token(token)
            .expires_in(expires_in.toEpochMilli())
            .build();

        return authInstructorResponseDTO;

    }
}
