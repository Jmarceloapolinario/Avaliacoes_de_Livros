package com.jm.Avaliacoes_de_Livros.Controller.Response;

import com.jm.Avaliacoes_de_Livros.Model.Comentarios;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record LivrosResponse (Long id , String titulo , String autor , String capa,
                              String sinopse, List<ComentariosResponse> comentarios, LocalDateTime createdAt, LocalDateTime updateAt){
}
