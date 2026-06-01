package com.jm.Avaliacoes_de_Livros.Mapper;

import com.jm.Avaliacoes_de_Livros.Controller.Request.ComentariosRequest;
import com.jm.Avaliacoes_de_Livros.Controller.Response.ComentariosResponse;
import com.jm.Avaliacoes_de_Livros.Model.Comentarios;
import com.jm.Avaliacoes_de_Livros.Model.Livros;
import lombok.Builder;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ComentariosMapper {



    public static Comentarios toComentario(ComentariosRequest request){



        return Comentarios
                .builder()
                .comentario(request.comentario())
                .estrelas(request.estrelas())
                .createdAt(request.createdAt())
                .updateAt(request.updateat())
                .build();
    }
    public static ComentariosResponse toComentarioResponse(Comentarios comentarios){
        return ComentariosResponse
                .builder()
                .id(comentarios.getId())
                .comentario(comentarios.getComentario())
                .estrelas(comentarios.getEstrelas())

                .build();

    }
}
