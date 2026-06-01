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
}
