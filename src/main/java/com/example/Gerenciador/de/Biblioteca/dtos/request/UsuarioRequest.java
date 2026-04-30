package com.example.Gerenciador.de.Biblioteca.dtos.request;

import java.time.LocalDate;

public record UsuarioRequest(
        String nome,
        String email,
        String cpf,
        String telefone,
        LocalDate dataCadastro
) {
}
