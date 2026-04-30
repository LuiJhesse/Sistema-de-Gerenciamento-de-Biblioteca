package com.example.Gerenciador.de.Biblioteca.dtos.request;

public record LivroRequest( String titulo,
                            String autor,
                            String editora,
                            Integer anoPublicacao,
                            Integer quantidadeDisponivel,
                            Long categoriaId) {
}
