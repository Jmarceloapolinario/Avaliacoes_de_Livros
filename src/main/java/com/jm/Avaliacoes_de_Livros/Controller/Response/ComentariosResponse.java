package com.jm.Avaliacoes_de_Livros.Controller.Response;

import com.jm.Avaliacoes_de_Livros.Model.Livros;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ComentariosResponse (Long id, String comentario, double estrelas ,
                                   LocalDateTime createdAt , LocalDateTime updateat){
}
