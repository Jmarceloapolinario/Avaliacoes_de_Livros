package com.jm.Avaliacoes_de_Livros.Controller.Response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record LivrosResponse (Long id ,String titulo , String autor , String capa,
                              String sinopse, LocalDateTime createdAt, LocalDateTime updateAt){
}
