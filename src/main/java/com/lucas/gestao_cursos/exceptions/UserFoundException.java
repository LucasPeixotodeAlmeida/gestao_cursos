package com.lucas.gestao_cursos.exceptions;

public class UserFoundException extends RuntimeException{
    public UserFoundException(){
        super("Usuário já cadastrado!");
    }
}
