package com.example.Gerenciador.de.Biblioteca.repositories;

import com.example.Gerenciador.de.Biblioteca.entities.Emprestimo;
import com.example.Gerenciador.de.Biblioteca.entities.StatusEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo,Long> {
    List<Emprestimo> findByUsuarioId(Long usuarioId);
    List<Emprestimo> findByLivroId(Long livroId);

    List<Emprestimo> findByStatusEmprestimo(StatusEmprestimo statusEmprestimo);
    List<Emprestimo> findByDataDevolucaoPrevistaBefore(LocalDate data);
}
