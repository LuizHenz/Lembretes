package com.example.exercicio.lembrete.Controller;

import com.example.exercicio.lembrete.DTO.PessoaDTO;
import com.example.exercicio.lembrete.Entity.Pessoa;
import com.example.exercicio.lembrete.Repository.PessoaRepository;
import com.example.exercicio.lembrete.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping("/{nome}")
    public ResponseEntity<?> findByNome(@PathVariable("nome") final String nome) {
        List<Pessoa> pessoas = pessoaService.findByNome(nome);
        if (pessoas.isEmpty()) {
            return ResponseEntity.badRequest().body("Nenhum registro encontrado");
        }
        return ResponseEntity.ok(pessoas);
    }
    @GetMapping("/{id}/lembretes")
    public ResponseEntity<?> listarPessoaELembretes(@PathVariable Long id) {
        try {
            Pessoa pessoa = pessoaService.buscarComLembretes(id);
            return ResponseEntity.ok(pessoa);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastrarPessoa(@RequestBody Pessoa pessoa) {
        try {
            pessoaService.cadastro(pessoa);
            return ResponseEntity.ok("Cadastro realizado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao cadastrar: " + e.getMessage());
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarPessoa(@PathVariable Long id, @RequestBody PessoaDTO pessoa) {
        try {
            pessoaService.editar(id, pessoa);
            return ResponseEntity.ok("Edição realizada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao editar: " + e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarPessoa(@PathVariable Long id) {
        try {
            pessoaService.deletar(id);
            return ResponseEntity.ok("Registro deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao deletar: " + e.getMessage());
        }
    }

}
