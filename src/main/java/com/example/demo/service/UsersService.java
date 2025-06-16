package com.example.demo.service;

import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public Users register(Users user) {
        return usersRepository.save(user);
    }

    public Optional<Users> login(String email, String senha) {
        return usersRepository.findByEmail(email)
                .filter(u -> u.getSenha().equals(senha));
    }

    public Optional<Users> findById(Long id) {
        return usersRepository.findById(id);
    }

    public Optional<Users> findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }
}
