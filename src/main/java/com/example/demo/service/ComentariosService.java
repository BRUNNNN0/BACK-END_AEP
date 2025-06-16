package com.example.demo.service;

import com.example.demo.model.Comentarios;
import com.example.demo.model.Posts;
import com.example.demo.repository.ComentariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentariosService {
    @Autowired
    ComentariosRepository comentariosRepository;

    public Comentarios addComentario(Comentarios comentario) {
        return comentariosRepository.save(comentario);
    }

    public List<Comentarios> getComentariosByPost(Posts post) {
        return comentariosRepository.findByPost(post);
    }
}
