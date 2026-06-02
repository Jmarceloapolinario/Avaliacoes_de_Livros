package com.jm.Avaliacoes_de_Livros.Service;


import com.jm.Avaliacoes_de_Livros.Model.Comentarios;
import com.jm.Avaliacoes_de_Livros.Model.Livros;
import com.jm.Avaliacoes_de_Livros.Repository.LivrosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LivrosService {

    private final LivrosRepository repository;
    private final ComentariosService comentariosService;

    public Livros save(Livros livro){
        return repository.save(livro);
    }

    public List<Livros> listar(){
        return repository.findAll();
    }
    public Livros findById(Long id){
        return repository.findById(id)  // retorna Optional<Livros>
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        // 👆 desempacota o Optional, ou lança exceção se vazio
    }
    public Optional<Livros> alterar(Long id , Livros alterarLivro){
            Optional<Livros> optLivro = repository.findById(id);
            if (optLivro.isPresent()){


                Livros livro = optLivro.get();
                livro.setTitulo(alterarLivro.getTitulo());
                livro.setCapa(alterarLivro.getCapa());
                livro.setSinopse(alterarLivro.getSinopse());
                livro.setAutor(alterarLivro.getAutor());

                repository.save(livro);

                return Optional.of(livro);
            }
            return Optional.empty();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }



}
