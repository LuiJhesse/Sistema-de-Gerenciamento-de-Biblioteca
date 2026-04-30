package com.example.Gerenciador.de.Biblioteca.dtos;

public record Livrodto(

        String titulo,
        String autor,
        String editora,
        Integer anoPublicacao,
        Integer quantidadeDisponivel,
        Long categoriaId
) {
}
