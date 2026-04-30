package com.example.Gerenciador.de.Biblioteca.services;

import com.example.Gerenciador.de.Biblioteca.dtos.Livrodto;
import com.example.Gerenciador.de.Biblioteca.entities.Categoria;
import com.example.Gerenciador.de.Biblioteca.entities.Livro;
import com.example.Gerenciador.de.Biblioteca.repositories.CategoriaRepository;
import com.example.Gerenciador.de.Biblioteca.repositories.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private LivroRepository repository;
    private CategoriaRepository categoriaRepository;

    private LivroService(LivroRepository repository, CategoriaRepository categoriaRepository){
        this.repository = repository;
        this.categoriaRepository = categoriaRepository;
    }

    public Livro cadastrarLivro(Livrodto livroDto) {

        if (livroDto.titulo() == null || livroDto.titulo().isBlank()) {
            throw new RuntimeException("O título do livro é obrigatório.");
        }

        if (livroDto.autor() == null || livroDto.autor().isBlank()) {
            throw new RuntimeException("O autor do livro é obrigatório.");
        }

        Categoria categoria = categoriaRepository.findById(livroDto.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));

        Optional<Livro> livroExistente =
                repository.findByTituloAndEditora(
                        livroDto.titulo(),
                        livroDto.editora()
                );

        if (livroExistente.isPresent()) {
            Livro livro = livroExistente.get();

            livro.setQuantidadeDisponivel(
                    livro.getQuantidadeDisponivel() + livroDto.quantidadeDisponivel()
            );

            return repository.save(livro);
        }

        Livro novoLivro = new Livro();
        novoLivro.setTitulo(livroDto.titulo());
        novoLivro.setAutor(livroDto.autor());
        novoLivro.setEditora(livroDto.editora());
        novoLivro.setAnoPublicacao(livroDto.anoPublicacao());
        novoLivro.setQuantidadeDisponivel(livroDto.quantidadeDisponivel());
        novoLivro.setCategoria(categoria);

        return repository.save(novoLivro);
    }


    public List<Livro> listarTodos() {
        return repository.findAll();
    }

    public Livro buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado."));
    }

    public void deletar(Long id) {
        Livro livro = buscarPorId(id);
        repository.delete(livro);
    }

    public List<Livro> listarPorCategoria(Long categoriaId) {
        return repository.findByCategoriaId(categoriaId);
    }


}
