package com.example.Gerenciador.de.Biblioteca.repositories;

import com.example.Gerenciador.de.Biblioteca.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    Optional<Categoria> findByNome(String nome);
}
