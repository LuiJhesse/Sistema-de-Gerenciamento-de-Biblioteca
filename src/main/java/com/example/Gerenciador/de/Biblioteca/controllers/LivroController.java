    package com.example.Gerenciador.de.Biblioteca.controllers;

    import com.example.Gerenciador.de.Biblioteca.dtos.request.LivroRequest;
    import com.example.Gerenciador.de.Biblioteca.entities.Livro;
    import com.example.Gerenciador.de.Biblioteca.services.LivroService;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/livros")
    public class LivroController {

        private final LivroService service;

        public LivroController(LivroService service) {
            this.service = service;
        }

        @PostMapping
        public ResponseEntity<Livro> cadastrar(@RequestBody LivroRequest request) {
            return ResponseEntity.ok(service.cadastrarLivro(request));
        }

        @GetMapping
        public ResponseEntity<List<Livro>> listarTodos() {
            return ResponseEntity.ok(service.listarTodos());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
            return ResponseEntity.ok(service.buscarPorId(id));
        }

        @GetMapping("/categoria/{categoriaId}")
        public ResponseEntity<List<Livro>> listarPorCategoria(@PathVariable Long categoriaId) {
            return ResponseEntity.ok(service.listarPorCategoria(categoriaId));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletar(@PathVariable Long id) {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        }
    }