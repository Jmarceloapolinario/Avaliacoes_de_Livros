package com.jm.Avaliacoes_de_Livros.Mapper;


import com.jm.Avaliacoes_de_Livros.Controller.Request.LivrosRequest;
import com.jm.Avaliacoes_de_Livros.Controller.Response.ComentariosResponse;
import com.jm.Avaliacoes_de_Livros.Controller.Response.LivrosResponse;
import com.jm.Avaliacoes_de_Livros.Model.Comentarios;
import com.jm.Avaliacoes_de_Livros.Model.Livros;
import lombok.experimental.UtilityClass;

import java.util.List;

import static java.util.stream.Collectors.toList;

@UtilityClass
public class LivrosMapper {

    public static Livros toLivro(LivrosRequest livrosRequest){


        return Livros
                .builder()
                .titulo(livrosRequest.titulo())
                .autor(livrosRequest.autor())
                .capa(livrosRequest.capa())
                .sinopse(livrosRequest.sinopse())
                .createdAt(livrosRequest.createdAt())
                .updateAt(livrosRequest.updateAt())
                .build();

    }

    public static LivrosResponse toLivrosResponse(Livros livros){

        List<ComentariosResponse> comentariosResponses = livros.getComentarios() == null
                ? List.of()  // 👈 retorna lista vazia se for null
                : livros.getComentarios().stream()
                .map(ComentariosMapper::toComentarioResponse)
                .toList();

        return LivrosResponse
                .builder()
                .id(livros.getId())
                .titulo(livros.getTitulo())
                .autor(livros.getAutor())
                .capa(livros.getCapa())
                .sinopse(livros.getSinopse())
                .comentarios(comentariosResponses)
                .build();
    }

}
