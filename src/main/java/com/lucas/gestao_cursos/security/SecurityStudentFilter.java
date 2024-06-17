package com.lucas.gestao_cursos.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
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

                if (header != null) {
                    var token = this.jwtProvider.validateToken(header);

                    if (token == null) {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    }

                    request.setAttribute("candidate_id", token.getSubject());
                    System.out.println(token);
                }

                filterChain.doFilter(request, response);
    }
    
}
