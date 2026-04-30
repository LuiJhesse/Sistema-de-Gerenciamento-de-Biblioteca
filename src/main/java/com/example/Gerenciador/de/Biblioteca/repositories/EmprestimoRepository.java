package com.example.Gerenciador.de.Biblioteca.repositories;

import com.example.Gerenciador.de.Biblioteca.entities.Emprestimo;
import com.example.Gerenciador.de.Biblioteca.entities.StatusEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo,Long> {
    List<Emprestimo> findByUsuarioId(Long usuarioId);
    List<Emprestimo> findByLivroId(Long livroId);
    List<Emprestimo> findByStatus(StatusEmprestimo status);
    List<Emprestimo> findByDataDevolucaoPrevistaBefore(LocalDate data);
}
