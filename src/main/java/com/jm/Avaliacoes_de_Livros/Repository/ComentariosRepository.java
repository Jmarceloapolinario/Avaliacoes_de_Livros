package com.jm.Avaliacoes_de_Livros.Repository;

import com.jm.Avaliacoes_de_Livros.Model.Comentarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentariosRepository extends JpaRepository<Comentarios, Long> {
}
