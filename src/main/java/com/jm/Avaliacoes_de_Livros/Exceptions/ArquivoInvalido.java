package com.jm.Avaliacoes_de_Livros.Exceptions;

public class ArquivoInvalido extends RuntimeException {
    public ArquivoInvalido(String message) {
        super(message);
    }

    public ArquivoInvalido(String message, Throwable cause) {
        super(message, cause);
    }
}