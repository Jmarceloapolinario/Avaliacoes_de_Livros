package com.jm.Avaliacoes_de_Livros.Mapper;


import com.jm.Avaliacoes_de_Livros.Controller.Request.LivrosRequest;
import com.jm.Avaliacoes_de_Livros.Controller.Response.LivrosResponse;
import com.jm.Avaliacoes_de_Livros.Model.Livros;
import lombok.experimental.UtilityClass;

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
        return LivrosResponse
                .builder()
                .id(livros.getId())
                .titulo(livros.getTitulo())
                .autor(livros.getAutor())
                .capa(livros.getCapa())
                .sinopse(livros.getSinopse())
                .build();
    }

}
