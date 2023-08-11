package com.example.exercicio.lembrete.Controller;

import com.example.exercicio.lembrete.Entity.Lembrete;
import com.example.exercicio.lembrete.Entity.Pessoa;
import com.example.exercicio.lembrete.Service.LembreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lembretes")
public class LembreteController {

    @Autowired
    private LembreteService lembreteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarLembrete(@RequestBody Lembrete lembrete) {
        try {
            lembreteService.cadastrarLembrete(lembrete.getPessoa().getId(), lembrete.getLembrete());
            return ResponseEntity.ok("Lembrete cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro ao cadastrar lembrete: " + e.getMessage());
        }
    }
}
