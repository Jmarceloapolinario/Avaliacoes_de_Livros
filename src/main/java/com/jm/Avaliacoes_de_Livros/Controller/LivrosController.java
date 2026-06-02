package com.jm.Avaliacoes_de_Livros.Controller;

import com.jm.Avaliacoes_de_Livros.Controller.Request.LivrosRequest;
import com.jm.Avaliacoes_de_Livros.Controller.Response.LivrosResponse;
import com.jm.Avaliacoes_de_Livros.Mapper.LivrosMapper;
import com.jm.Avaliacoes_de_Livros.Model.Comentarios;
import com.jm.Avaliacoes_de_Livros.Model.Livros;
import com.jm.Avaliacoes_de_Livros.Service.ComentariosService;
import com.jm.Avaliacoes_de_Livros.Service.LivrosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("livros/livro")
@RequiredArgsConstructor
public class LivrosController {

    private static String caminhoCapa = "D:\\imgLivro/";

    private final LivrosService service;
    private final ComentariosService comentariosService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LivrosResponse> save(
            @RequestPart("dados") LivrosRequest livrosRequest,
            @RequestPart("file") MultipartFile arquivo){
        try {
            if (!arquivo.isEmpty()){
                byte[] bytes = arquivo.getBytes();
                Path caminho = Paths.get(caminhoCapa+String.valueOf(livrosRequest.titulo())+arquivo.getOriginalFilename());
                Files.write(caminho, bytes);

                livrosRequest = new LivrosRequest(
                        livrosRequest.titulo(),
                        livrosRequest.autor(),
                        caminho.toString(),
                        livrosRequest.sinopse(),
                        livrosRequest.createdAt(),
                        livrosRequest.updateAt()

                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Livros savedLivro = service.save(LivrosMapper.toLivro(livrosRequest));
        return ResponseEntity.ok(LivrosMapper.toLivrosResponse(savedLivro));
    }
    @GetMapping("/listar")
    public ResponseEntity<List<LivrosResponse>> listar() {
        List<LivrosResponse> lista = service.listar()
                .stream()
                .map(LivrosMapper::toLivrosResponse)
                .toList();
        return ResponseEntity.ok(lista);
    }


    @GetMapping("/listar/{id}")
    public ResponseEntity<LivrosResponse> findById(@PathVariable Long id){
        Livros livro = service.findById(id);
        return ResponseEntity.ok(LivrosMapper.toLivrosResponse(livro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivrosResponse> alterarLivro(@PathVariable Long id, @RequestPart("dados") LivrosRequest request, @RequestPart("file") MultipartFile arquivo){
        try {
            if (!arquivo.isEmpty()){
                byte[] bytes = arquivo.getBytes();
                Path caminho = Paths.get(caminhoCapa+String.valueOf(request.titulo())+arquivo.getOriginalFilename());
                Files.write(caminho, bytes);

                request = new LivrosRequest(
                        request.titulo(),
                        request.autor(),
                        caminho.toString(),
                        request.sinopse(),
                        request.createdAt(),
                        request.updateAt()

                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return service.alterar(id, LivrosMapper.toLivro(request))
                .map(livros -> ResponseEntity.ok(LivrosMapper.toLivrosResponse(livros)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delte(@PathVariable Long id){
       Livros livro = service.findById(id);
       if (livro != null){
            service.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
       }
       return ResponseEntity.notFound().build();

    }
}
