package com.example.Gerenciador.de.Biblioteca.services;

import com.example.Gerenciador.de.Biblioteca.dtos.UsuarioDto;
import com.example.Gerenciador.de.Biblioteca.entities.Usuario;
import com.example.Gerenciador.de.Biblioteca.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario cadastrarUsuario(UsuarioDto usuarioDto) {

        if (usuarioDto.nome() == null || usuarioDto.nome().isBlank()) {
            throw new RuntimeException("O nome do usuário é obrigatório.");
        }

        if (usuarioDto.email() == null || usuarioDto.email().isBlank()) {
            throw new RuntimeException("O email do usuário é obrigatório.");
        }

        if (usuarioDto.cpf() == null || usuarioDto.cpf().isBlank()) {
            throw new RuntimeException("O CPF do usuário é obrigatório.");
        }

        if (repository.findByEmail(usuarioDto.email()).isPresent()) {
            throw new RuntimeException("Já existe um usuário com esse email.");
        }

        if (repository.findByCpf(usuarioDto.cpf()).isPresent()) {
            throw new RuntimeException("Já existe um usuário com esse CPF.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDto.nome());
        usuario.setEmail(usuarioDto.email());
        usuario.setCpf(usuarioDto.cpf());
        usuario.setTelefone(usuarioDto.telefone());
        usuario.setDataCadastro(LocalDate.now());

        return repository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    public void deletar(Long id) {
        Usuario usuario = buscarPorId(id);
        repository.delete(usuario);
    }
}