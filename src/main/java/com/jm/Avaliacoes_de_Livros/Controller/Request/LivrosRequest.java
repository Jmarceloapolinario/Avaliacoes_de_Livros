package com.jm.Avaliacoes_de_Livros.Controller.Request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


public record LivrosRequest (String titulo , String autor , String capa,
                             String sinopse, LocalDateTime createdAt, LocalDateTime updateAt){
}
