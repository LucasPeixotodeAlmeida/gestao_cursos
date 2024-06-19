package com.lucas.gestao_cursos.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lucas.gestao_cursos.providers.JWTStudentProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityStudentFilter extends OncePerRequestFilter{

    @Autowired
    private JWTStudentProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                SecurityContextHolder.getContext().setAuthentication(null);
                String header = request.getHeader("Authorization");

                if(request.getRequestURI().startsWith("/student")){
                    if (header != null) {
                        var token = this.jwtProvider.validateToken(header);
    
                        if (token == null) {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        }
    
                        request.setAttribute("student_id", token.getSubject());
                        var roles = token.getClaim("roles").asList(Object.class);
                        var grants = roles.stream().map(
                            role -> new SimpleGrantedAuthority(role.toString())
                        ).toList();
                    
                    UsernamePasswordAuthenticationToken auth = 
                        new UsernamePasswordAuthenticationToken(token.getSubject(), null, grants);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    }
                }

                filterChain.doFilter(request, response);
    }
    
}
