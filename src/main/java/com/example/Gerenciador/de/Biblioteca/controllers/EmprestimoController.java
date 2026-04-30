package com.example.Gerenciador.de.Biblioteca.controllers;

import com.example.Gerenciador.de.Biblioteca.entities.Emprestimo;
import com.example.Gerenciador.de.Biblioteca.services.EmprestimoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoService service;

    public EmprestimoController(EmprestimoService service) {
        this.service = service;
    }

    @PostMapping("/realizar")
    public ResponseEntity<Emprestimo> realizarEmprestimo(
            @RequestParam Long usuarioId,
            @RequestParam Long livroId
    ) {
        return ResponseEntity.ok(service.realizarEmprestimo(usuarioId, livroId));
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/devolver/{id}")
    public ResponseEntity<Void> devolver(@PathVariable Long id) {
        service.devolverLivro(id);
        return ResponseEntity.noContent().build();
    }
}