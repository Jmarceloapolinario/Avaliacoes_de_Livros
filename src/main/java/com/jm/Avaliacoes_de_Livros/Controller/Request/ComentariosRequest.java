package com.jm.Avaliacoes_de_Livros.Controller.Request;

import com.jm.Avaliacoes_de_Livros.Model.Livros;

import java.time.LocalDateTime;

public record ComentariosRequest (String comentario, double estrelas , Long livroId ,
                                  LocalDateTime createdAt , LocalDateTime updateat){
}
