package com.jm.Avaliacoes_de_Livros.Service;


import com.jm.Avaliacoes_de_Livros.Model.Livros;
import com.jm.Avaliacoes_de_Livros.Repository.LivrosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LivrosService {

    private final LivrosRepository repository;

    public Livros save(Livros livro){
        return repository.save(livro);
    }

    public List<Livros> listar(){
        return repository.findAll();
    }
    public Optional<Livros> listarId(Long id){
        return repository.findById(id);
    }


}
