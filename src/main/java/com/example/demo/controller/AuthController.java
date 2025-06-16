package com.example.demo.controller;


import com.example.demo.model.Users;
import com.example.demo.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UsersService usersService;

    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody Users user) {
        Users savedUser = usersService.register(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users loginRequest) {
        Optional<Users> userOptional = usersService.findByEmail(loginRequest.getEmail());

        if (userOptional.isPresent() && userOptional.get().getSenha().equals(loginRequest.getSenha())) {
            Users user = userOptional.get();

            // Monta a resposta com apenas os dados necessários
            Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("name", user.getName());
            response.put("email", user.getEmail());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Email ou senha inválidos.");
        }
    }
}