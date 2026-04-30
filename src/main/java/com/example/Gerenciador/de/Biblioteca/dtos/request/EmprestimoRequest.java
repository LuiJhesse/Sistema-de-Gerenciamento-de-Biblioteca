package com.example.Gerenciador.de.Biblioteca.dtos.request;

public record EmprestimoRequest(
        Long usuarioId,
        Long livroId
) {
}
