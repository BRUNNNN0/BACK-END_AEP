package com.example.demo.controller;

import com.example.demo.model.Comentarios;
import com.example.demo.model.Posts;
import com.example.demo.model.Users;
import com.example.demo.service.ComentariosService;
import com.example.demo.service.PostsService;
import com.example.demo.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comentarios")
@RequiredArgsConstructor
public class ComentarioController {
    @Autowired
    private ComentariosService comentarioService;
    @Autowired
    private PostsService postService;
    @Autowired
    private UsersService usersService;

    @PostMapping("/add")
    public ResponseEntity<?> addComentario(@RequestBody Comentarios comentario,
                                           @RequestParam Long userId,
                                           @RequestParam Long postId) {
        Optional<Users> user = usersService.findById(userId);
        Optional<Posts> post = postService.getPostById(postId);

        if (user.isEmpty()) return ResponseEntity.status(404).body("Usuário não encontrado.");
        if (post.isEmpty()) return ResponseEntity.status(404).body("Post não encontrado.");

        comentario.setAutor(user.get());
        comentario.setPost(post.get());

        return ResponseEntity.ok(comentarioService.addComentario(comentario));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getComentariosByPost(@PathVariable Long postId) {
        return postService.getPostById(postId)
                .map(post -> {
                    // Obtém os comentários associados ao post
                    List<Comentarios> comentarios = comentarioService.getComentariosByPost(post);
                    // Se a lista de comentários estiver vazia, retorne um 204 No Content
                    if (comentarios.isEmpty()) {
                        return ResponseEntity.status(204).body("Nenhum comentário encontrado.");
                    }
                    return ResponseEntity.ok(comentarios);
                })
                .orElseGet(() -> ResponseEntity.status(404).body("Post não encontrado."));
    }
}
