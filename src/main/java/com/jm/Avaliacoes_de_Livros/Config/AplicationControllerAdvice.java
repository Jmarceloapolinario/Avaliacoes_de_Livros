package com.jm.Avaliacoes_de_Livros.Config;

import com.jm.Avaliacoes_de_Livros.Exceptions.ComentarioEmpty;
import com.jm.Avaliacoes_de_Livros.Exceptions.LivroEmpty;
import com.jm.Avaliacoes_de_Livros.Exceptions.LivroNotPresent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AplicationControllerAdvice {

    @ExceptionHandler(LivroNotPresent.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handerNotFoundException(LivroNotPresent ex){
        return ex.getMessage();
    }
    @ExceptionHandler(LivroEmpty.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handerEmpty(LivroEmpty ex){
        return ex.getMessage();
    }
    @ExceptionHandler(ComentarioEmpty.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handerComentarioEmpty(ComentarioEmpty ex){
        return ex.getMessage();
    }
}
