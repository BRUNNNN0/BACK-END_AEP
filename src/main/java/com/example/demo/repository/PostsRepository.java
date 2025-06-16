package com.example.demo.repository;

import com.example.demo.model.Posts;
import com.example.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    List<Posts> findByAutor(Users autor); // retorna posts de um usuário específico
}