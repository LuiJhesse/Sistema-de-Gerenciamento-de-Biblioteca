package com.example.Gerenciador.de.Biblioteca.dtos;

import java.time.LocalDate;

public record UsuarioDto(String nome,
                         String email,
                         String cpf,
                         String telefone,
                         LocalDate dataCadastro) {
}
