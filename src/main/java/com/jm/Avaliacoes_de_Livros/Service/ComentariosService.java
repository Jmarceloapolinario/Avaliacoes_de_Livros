package com.jm.Avaliacoes_de_Livros.Service;

import com.jm.Avaliacoes_de_Livros.Controller.Request.ComentariosRequest;
import com.jm.Avaliacoes_de_Livros.Exceptions.ComentarioEmpty;
import com.jm.Avaliacoes_de_Livros.Exceptions.LivroNotPresent;
import com.jm.Avaliacoes_de_Livros.Model.Comentarios;
import com.jm.Avaliacoes_de_Livros.Repository.ComentariosRepository;
import com.jm.Avaliacoes_de_Livros.Repository.LivrosRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComentariosService {

    private final ComentariosRepository repository;
    private final LivrosRepository livrosRepository;

    public Comentarios save(Comentarios comentario){
        if (comentario.getComentario().isBlank()){
            throw new ComentarioEmpty("O cometario esta vazio");
        }
        Long livroId = comentario.getLivro().getId();
        if (livrosRepository.findById(livroId).isEmpty()){
            throw new LivroNotPresent("Esse livro não existe");
        }

        return repository.save(comentario);
    }
    public List<Comentarios> listar(){
        return repository.findAll();
    }
    public Optional<Comentarios> listarId(Long id){
        return repository.findById(id);
    }
    public Optional<Comentarios> alterarComentario(Long id, Comentarios alterarComentario){
        Optional<Comentarios> optionalComentarios = repository.findById(id);
        if (optionalComentarios.isPresent()){

            Comentarios comentario = optionalComentarios.get();
            comentario.setComentario(alterarComentario.getComentario());
            comentario.setEstrelas(alterarComentario.getEstrelas());

            repository.save(comentario);

            return Optional.of(comentario);

        }
        throw new ComentarioEmpty("O comentario esta vazio");
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }
}
