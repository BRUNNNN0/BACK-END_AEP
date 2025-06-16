package com.example.demo.service;

import com.example.demo.model.Posts;
import com.example.demo.model.Users;
import com.example.demo.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostsService {
    @Autowired
    PostsRepository postsRepository;
    public Posts createPost(Posts post) {
        return postsRepository.save(post);
    }

    public List<Posts> getAllPosts() {
        return postsRepository.findAll();
    }

    public List<Posts> getPostsByUser(Users user) {
        return postsRepository.findByAutor(user);
    }

    public Optional<Posts> getPostById(Long id) {
        return postsRepository.findById(id);
    }

    public void deletePost(Long id) {
        postsRepository.deleteById(id);
    }

    public Posts updatePost(Posts post) {
        return postsRepository.save(post);
    }
}

