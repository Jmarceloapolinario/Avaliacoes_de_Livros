package com.jm.Avaliacoes_de_Livros.Controller;

import com.jm.Avaliacoes_de_Livros.Controller.Request.ComentariosRequest;
import com.jm.Avaliacoes_de_Livros.Controller.Response.ComentariosResponse;
import com.jm.Avaliacoes_de_Livros.Controller.Response.LivrosResponse;
import com.jm.Avaliacoes_de_Livros.Mapper.ComentariosMapper;
import com.jm.Avaliacoes_de_Livros.Mapper.LivrosMapper;
import com.jm.Avaliacoes_de_Livros.Model.Comentarios;
import com.jm.Avaliacoes_de_Livros.Model.Livros;
import com.jm.Avaliacoes_de_Livros.Service.ComentariosService;
import com.jm.Avaliacoes_de_Livros.Service.LivrosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.cglib.core.CodeEmitter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("livros/comentarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "${app.frontend.url}")
@Tag(name = "Comentarios", description = "Recurso responsavel por gerenciar os comentarios")
public class ComentarioController {

    private final ComentariosService service;
    private final LivrosService livrosService;

    @Operation(summary = "Cria um novo comentario" , description = "Adiciona um comentario a um livro.")
    @ApiResponse(responseCode = "201" , description = "Comentario adicionado com sucesso.",
    content = @Content(schema = @Schema(implementation = ComentariosRequest.class)))
    @PostMapping({"/"})
    public ResponseEntity<ComentariosResponse> save(@RequestBody ComentariosRequest request){
        Comentarios comentario = ComentariosMapper.toComentario(request);

        Livros livro = livrosService.findById(request.livroId());
        comentario.setLivro(livro);

        Comentarios savedComentario = service.save(comentario);
        return ResponseEntity.status(HttpStatus.CREATED).body(ComentariosMapper.toComentarioResponse(savedComentario));
    }
    @Operation(summary = "Altera um comentario", description = "Metodo responsavel por alterar um comentario.")
    @ApiResponse(responseCode = "200" , description = "Comentario alterado com sucesso.",
    content = @Content(schema = @Schema(implementation = ComentariosRequest.class)))
    @PutMapping("/{id}")
    public ResponseEntity<ComentariosResponse> alterarComentario(@PathVariable Long id, @RequestBody ComentariosRequest request){
        return service.alterarComentario(id, ComentariosMapper.toComentario(request))
                .map(comentarios -> ResponseEntity.ok(ComentariosMapper.toComentarioResponse(comentarios)))
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Deleta um comentario", description = "Metodo responsavel por deletar um comentario.")
    @ApiResponse(responseCode = "204", description = "Comentario deletado com sucesso.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComentario(@PathVariable Long id){
         Optional<Comentarios> comentario = service.listarId(id);
         if (comentario.isPresent()){
             service.deletar(id);
             return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
         }
         return ResponseEntity.notFound().build();
    }

}
