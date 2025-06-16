package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Users autor;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;
    private String endereco;

    @Enumerated(EnumType.STRING)
    private TipoPost tipo;
    private LocalDateTime dataCriacao;

    private int curtidas;
    private String urlImage;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Comentarios> comentarios;

}
