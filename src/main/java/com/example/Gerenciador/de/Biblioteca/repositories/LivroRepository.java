package com.example.Gerenciador.de.Biblioteca.repositories;

import com.example.Gerenciador.de.Biblioteca.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByAutor(String autor);

    List<Livro> findByTituloContainingIgnoreCase(String titulo);

    Optional<Livro> findById(Long id);

    List<Livro> findByCategoriaId(Long categoriaId);

    Optional<Livro> findByTituloAndEditora(String titulo, String editora);
}