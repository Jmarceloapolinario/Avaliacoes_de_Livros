package com.jm.Avaliacoes_de_Livros.Repository;


import com.jm.Avaliacoes_de_Livros.Model.Livros;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivrosRepository extends JpaRepository<Livros, Long> {
}
