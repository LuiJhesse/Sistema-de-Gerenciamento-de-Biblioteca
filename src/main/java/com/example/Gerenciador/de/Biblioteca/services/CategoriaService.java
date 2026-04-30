package com.example.Gerenciador.de.Biblioteca.services;

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

    public Categoria salvar(Categoria categoria) {

        if (categoria.getNome() == null || categoria.getNome().isBlank()) {
            throw new RuntimeException("O nome da categoria é obrigatório.");
        }

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