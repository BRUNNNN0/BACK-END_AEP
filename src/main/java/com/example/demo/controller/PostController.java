package com.example.demo.controller;

import com.example.demo.model.Posts;
import com.example.demo.model.Users;
import com.example.demo.service.PostsService;
import com.example.demo.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    @Autowired
    private PostsService postService;
    @Autowired
    private UsersService usersService;

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody Posts post, @RequestParam Long userId) {
        // Busca o usuário pelo ID
        Optional<Users> userOptional = usersService.findById(userId);

        if (userOptional.isPresent()) {
            Users user = userOptional.get();

            // Associa o post ao usuário
            post.setAutor(user);

            // Cria o post e retorna com status 201 Created
            Posts createdPost = postService.createPost(post);

            return ResponseEntity.status(201).body(createdPost); // Retorna o post criado
        } else {
            return ResponseEntity.status(404).body("Usuário não encontrado."); // Caso o usuário não seja encontrado
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getUserPosts(@PathVariable Long userId) {
        Optional<Users> optionalUser = usersService.findById(userId);

        if (optionalUser.isPresent()) {
            List<Posts> posts = postService.getPostsByUser(optionalUser.get());
            return ResponseEntity.ok(posts); // ✅ tipo: ResponseEntity<List<Posts>>
        } else {
            return ResponseEntity.status(404).body("Usuário não encontrado."); // ✅ tipo: ResponseEntity<String>
        }
    }

    @GetMapping
    public List<Posts> getAllPosts() {
        return postService.getAllPosts();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Object> updatePost(@PathVariable Long postId, @RequestBody Posts updatedPost) {
        Optional<Posts> postOptional = postService.getPostById(postId);

        if (postOptional.isPresent()) {
            Posts existing = postOptional.get();
            updatedPost.setId(postId);
            updatedPost.setAutor(existing.getAutor());
            Posts updated = postService.updatePost(updatedPost);
            return ResponseEntity.ok(updated); // ✅ tipo Posts
        } else {
            return ResponseEntity.status(404).body("Post não encontrado."); // ✅ tipo String
        }
    }
}
