package com.example.demo.repository;

import com.example.demo.model.Comentarios;
import com.example.demo.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentariosRepository extends JpaRepository<Comentarios, Long> {

    List<Comentarios> findByPost(Posts post); // retorna comentários de um post específico
}
