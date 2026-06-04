package com.jm.Avaliacoes_de_Livros.Service;

import com.jm.Avaliacoes_de_Livros.Controller.Request.ComentariosRequest;
import com.jm.Avaliacoes_de_Livros.Model.Comentarios;
import com.jm.Avaliacoes_de_Livros.Repository.ComentariosRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComentariosService {

    private final ComentariosRepository repository;

    public Comentarios save(Comentarios comentario){
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
        return Optional.empty();
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }
}
