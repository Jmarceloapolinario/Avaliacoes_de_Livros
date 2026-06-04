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
import lombok.RequiredArgsConstructor;
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
@CrossOrigin(origins = "\"http://localhost:8081\"")
public class ComentarioController {

    private final ComentariosService service;
    private final LivrosService livrosService;

    @PostMapping({"", "/"})
    public ResponseEntity<ComentariosResponse> save(@RequestBody ComentariosRequest request){
        Comentarios comentario = ComentariosMapper.toComentario(request);

        Livros livro = livrosService.findById(request.livroId());
        comentario.setLivro(livro);

        Comentarios savedComentario = service.save(comentario);
        return ResponseEntity.ok(ComentariosMapper.toComentarioResponse(savedComentario));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ComentariosResponse> alterarComentario(@PathVariable Long id, @RequestBody ComentariosRequest request){
        return service.alterarComentario(id, ComentariosMapper.toComentario(request))
                .map(comentarios -> ResponseEntity.ok(ComentariosMapper.toComentarioResponse(comentarios)))
                .orElse(ResponseEntity.notFound().build());
    }
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
