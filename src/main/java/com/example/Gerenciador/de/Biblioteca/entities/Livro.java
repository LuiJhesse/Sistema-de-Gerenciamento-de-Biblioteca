package com.example.Gerenciador.de.Biblioteca.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "livros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String autor;

    private String editora;

    private Integer anoPublicacao;

    private Integer quantidadeDisponivel;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Enumerated(EnumType.STRING)
    private Status status;
}