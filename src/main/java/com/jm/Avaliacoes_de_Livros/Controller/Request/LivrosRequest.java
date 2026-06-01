package com.jm.Avaliacoes_de_Livros.Controller.Request;

import com.jm.Avaliacoes_de_Livros.Model.Comentarios;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


public record LivrosRequest (String titulo , String autor , String capa,
                             String sinopse, LocalDateTime createdAt, LocalDateTime updateAt){
}
