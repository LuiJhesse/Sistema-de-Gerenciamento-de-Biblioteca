package com.example.Gerenciador.de.Biblioteca.services;

import com.example.Gerenciador.de.Biblioteca.dtos.request.UsuarioRequest;
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

    public Usuario cadastrarUsuario(UsuarioRequest usuarioRequest) {

        if (usuarioRequest.nome() == null || usuarioRequest.nome().isBlank()) {
            throw new RuntimeException("O nome do usuário é obrigatório.");
        }

        if (usuarioRequest.email() == null || usuarioRequest.email().isBlank()) {
            throw new RuntimeException("O email do usuário é obrigatório.");
        }

        if (usuarioRequest.cpf() == null || usuarioRequest.cpf().isBlank()) {
            throw new RuntimeException("O CPF do usuário é obrigatório.");
        }

        if (repository.findByEmail(usuarioRequest.email()).isPresent()) {
            throw new RuntimeException("Já existe um usuário com esse email.");
        }

        if (repository.findByCpf(usuarioRequest.cpf()).isPresent()) {
            throw new RuntimeException("Já existe um usuário com esse CPF.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequest.nome());
        usuario.setEmail(usuarioRequest.email());
        usuario.setCpf(usuarioRequest.cpf());
        usuario.setTelefone(usuarioRequest.telefone());
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