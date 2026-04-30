package com.example.Gerenciador.de.Biblioteca.services;

import com.example.Gerenciador.de.Biblioteca.entities.Emprestimo;
import com.example.Gerenciador.de.Biblioteca.entities.Livro;
import com.example.Gerenciador.de.Biblioteca.entities.Usuario;
import com.example.Gerenciador.de.Biblioteca.repositories.EmprestimoRepository;
import com.example.Gerenciador.de.Biblioteca.repositories.LivroRepository;
import com.example.Gerenciador.de.Biblioteca.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmprestimoServiceTest {

    @InjectMocks
    private EmprestimoService service;

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private EmprestimoRepository emprestimoRepository;

    @Test
    void deveRealizarEmprestimoComSucesso() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);

        Livro livro = new Livro();
        livro.setId(1L);
        livro.setQuantidadeDisponivel(5);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));
        when(emprestimoRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Emprestimo result = service.realizarEmprestimo(1L, 1L);

        assertNotNull(result);
        assertEquals(4, livro.getQuantidadeDisponivel());
    }
}
