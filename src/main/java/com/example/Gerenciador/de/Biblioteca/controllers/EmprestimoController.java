package com.example.Gerenciador.de.Biblioteca.controllers;

import com.example.Gerenciador.de.Biblioteca.dtos.request.EmprestimoRequest;
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
    @PostMapping
    public ResponseEntity<Emprestimo> realizarEmprestimo(
            @RequestBody EmprestimoRequest request
    ) {
        return ResponseEntity.ok(
                service.realizarEmprestimo(request)
        );
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
    public ResponseEntity<String> devolver(@PathVariable Long id) {

        boolean devolvido = service.devolverLivro(id);

        if (!devolvido) {
            return ResponseEntity.badRequest()
                    .body("Esse livro já foi devolvido.");
        }

        return ResponseEntity.ok("Livro devolvido com sucesso.");
    }
    @PutMapping("/verificar-atrasos")
    public ResponseEntity<String> verificarAtrasos() {
        service.verificarAtrasos();
        return ResponseEntity.ok("Verificação de atrasos realizada com sucesso.");
    }
}