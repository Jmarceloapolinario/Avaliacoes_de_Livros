package com.jm.Avaliacoes_de_Livros.Exceptions;

public class LivroNotPresent extends RuntimeException {
    public LivroNotPresent(String message) {
        super(message);
    }
}
