package com.example.Gerenciador.de.Biblioteca.services;

import com.example.Gerenciador.de.Biblioteca.dtos.request.CategoriaRequest;
import com.example.Gerenciador.de.Biblioteca.entities.Categoria;
import com.example.Gerenciador.de.Biblioteca.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public Categoria salvar(CategoriaRequest categoriaRequest) {

        if (categoriaRequest.nome() == null || categoriaRequest.nome().isBlank()) {
            throw new RuntimeException("O nome da categoria é obrigatório.");
        }

        Categoria categoria = new Categoria();
        categoria.setNome(categoriaRequest.nome());
        categoria.setDescricao(categoriaRequest.descricao());

        return repository.save(categoria);
    }

    public List<Categoria> listarTodas() {
        return repository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));
    }

    public void deletar(Long id) {
        Categoria categoria = buscarPorId(id);
        repository.delete(categoria);
    }
}