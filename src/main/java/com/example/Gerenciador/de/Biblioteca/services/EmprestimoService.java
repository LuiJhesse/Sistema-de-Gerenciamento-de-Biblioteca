package com.example.Gerenciador.de.Biblioteca.services;

import com.example.Gerenciador.de.Biblioteca.dtos.request.EmprestimoRequest;
import com.example.Gerenciador.de.Biblioteca.entities.Emprestimo;
import com.example.Gerenciador.de.Biblioteca.entities.Livro;
import com.example.Gerenciador.de.Biblioteca.entities.Usuario;
import com.example.Gerenciador.de.Biblioteca.repositories.EmprestimoRepository;
import com.example.Gerenciador.de.Biblioteca.repositories.LivroRepository;
import com.example.Gerenciador.de.Biblioteca.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;

    public EmprestimoService(
            EmprestimoRepository emprestimoRepository,
            LivroRepository livroRepository,
            UsuarioRepository usuarioRepository
    ) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Emprestimo realizarEmprestimo(EmprestimoRequest emprestimoRequest) {

        Usuario usuario = usuarioRepository.findById(emprestimoRequest.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        Livro livro = livroRepository.findById(emprestimoRequest.livroId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado."));

        if (livro.getQuantidadeDisponivel() <= 0) {
            throw new RuntimeException("Livro indisponível para empréstimo.");
        }

        livro.setQuantidadeDisponivel(
                livro.getQuantidadeDisponivel() - 1
        );

        livroRepository.save(livro);

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucaoPrevista(LocalDate.now().plusDays(31));

        return emprestimoRepository.save(emprestimo);
    }

    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

    public Emprestimo buscarPorId(Long id) {
        return emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado."));
    }

    public void devolverLivro(Long emprestimoId) {

        Emprestimo emprestimo = buscarPorId(emprestimoId);

        Livro livro = emprestimo.getLivro();

        livro.setQuantidadeDisponivel(
                livro.getQuantidadeDisponivel() + 1
        );

        livroRepository.save(livro);

        emprestimo.setDataDevolucaoReal(LocalDate.now());

        emprestimoRepository.save(emprestimo);
    }
}
