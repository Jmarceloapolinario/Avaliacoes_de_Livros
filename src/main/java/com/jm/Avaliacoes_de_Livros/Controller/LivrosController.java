package com.jm.Avaliacoes_de_Livros.Controller;

import com.jm.Avaliacoes_de_Livros.Controller.Request.LivrosRequest;
import com.jm.Avaliacoes_de_Livros.Controller.Response.LivrosResponse;
import com.jm.Avaliacoes_de_Livros.Exceptions.ArquivoInvalido;
import com.jm.Avaliacoes_de_Livros.Exceptions.LivroEmpty;
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
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("livros/livro")
@RequiredArgsConstructor
@CrossOrigin(origins = "${app.frontend.url}")
@Tag(name = "Livros" , description = "Recurso responsavel por gerenciar os livros")
public class LivrosController {

    @Value("${app.upload.dir}")
    private String caminhoCapa;

    private final LivrosService service;

    private static final Set<String> ExtesoesPermitidas = Set.of(".png", ".jpg", ".jpeg");

    @Operation(summary = "Cria o diretorio caso ele nao exista")
    @PostConstruct
    public void garantirDiretorioDeUpload() throws IOException {
        Files.createDirectories(Paths.get(caminhoCapa));
    }

    @Operation(summary = "Buscar a imagem especifica do livro" , description = "Buscar a imagem vinculada a um livro e retorna para o frontend.")
    @ApiResponse(responseCode = "200" , description = "A imagem")
    @GetMapping("/{id}/imagem")
    public ResponseEntity<Resource> buscarImagem(@PathVariable Long id) {
        try {

            Livros livro = service.findById(id);
            String caminhoDaImagem = livro.getCapa();

            if (caminhoDaImagem == null || caminhoDaImagem.isEmpty()) {
                return ResponseEntity.notFound().build();
            }


            Path path = Paths.get(caminhoDaImagem);
            Resource resource = new UrlResource(path.toUri());

            if (resource.exists() && resource.isReadable()) {

                String contentType = caminhoDaImagem.toLowerCase().endsWith(".png")
                        ? MediaType.IMAGE_PNG_VALUE
                        : MediaType.IMAGE_JPEG_VALUE;

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (MalformedURLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Cria um novo livro" , description = "Metodo responsavel por criar um novo livro.")
    @ApiResponse(responseCode = "201" , description = "Livro adicionado com sucesso",
    content = @Content(schema = @Schema(implementation = LivrosRequest.class)))
    @PostMapping(value = {"/"},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LivrosResponse> save(@RequestPart("dados") LivrosRequest livrosRequest, @RequestPart("file") MultipartFile arquivo){
        if (arquivo.isEmpty()){
            throw new LivroEmpty("O livro esta vazio");
        }

        try {
            String nomeOriginal = arquivo.getOriginalFilename();
            String extensao = nomeOriginal.substring(nomeOriginal.lastIndexOf("."));

            if (!ExtesoesPermitidas.contains(extensao)){
                throw new ArquivoInvalido("Esse tipo de arquivo é invalido");
            }
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

        } catch (IOException e) {
            e.printStackTrace();
        }

        Livros savedLivro = service.save(LivrosMapper.toLivro(livrosRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(LivrosMapper.toLivrosResponse(savedLivro));
    }
    @Operation(summary = "Lista todos os livros" , description = "Metodo responsavel por listar todos os livros.")
    @ApiResponse(responseCode = "200" , description = "Lista de livros",
    content = @Content(schema = @Schema(implementation = LivrosResponse.class)))
    @GetMapping("/listar")
    public ResponseEntity<List<LivrosResponse>> listar() {
        List<LivrosResponse> lista = service.listar()
                .stream()
                .map(LivrosMapper::toLivrosResponse)
                .toList();
        return ResponseEntity.ok(lista);
    }


    @Operation(summary = "Lista um livro especifico" , description = "Metodo responsavel por lista um livro por ID.")
    @ApiResponse(responseCode = "200", description = "Livro",
    content = @Content(schema = @Schema(implementation = LivrosResponse.class)))
    @GetMapping("/listar/{id}")
    public ResponseEntity<LivrosResponse> findById(@PathVariable Long id){
        Livros livro = service.findById(id);
        return ResponseEntity.ok(LivrosMapper.toLivrosResponse(livro));
    }

    @Operation(summary = "Altera um livro", description = "Metodo responsavel para alterar um livro selecionado por um ID.")
    @ApiResponse(responseCode = "200", description = "Livro alterado com sucesso",
    content = @Content(schema = @Schema(implementation = LivrosRequest.class)))
    @PutMapping("/{id}")
    public ResponseEntity<LivrosResponse> alterarLivro(@PathVariable Long id, @RequestPart("dados") LivrosRequest request, @RequestPart("file") MultipartFile arquivo){
       if(arquivo.isEmpty()){
           throw new LivroEmpty("O Livro esta vazio");
       }
       try{
           byte[] bytes = arquivo.getBytes();
           Path caminho = Paths.get(caminhoCapa,request.titulo() + arquivo.getOriginalFilename());
           Files.write(caminho,bytes);

           request = new LivrosRequest(
                   request.titulo(),
                   request.autor(),
                   caminho.toString(),
                   request.sinopse(),
                   request.createdAt(),
                   request.updateAt()
           );
       }catch (IOException e){
           e.printStackTrace();
       }
        return service.alterar(id, LivrosMapper.toLivro(request))
                .map(livros -> ResponseEntity.ok(LivrosMapper.toLivrosResponse(livros)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deleta um livro", description = "Deleta o livro selecionado por um ID.")
    @ApiResponse(responseCode = "204" , description = "Livro deletado com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
