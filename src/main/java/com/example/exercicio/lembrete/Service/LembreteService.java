package com.example.exercicio.lembrete.Service;

import com.example.exercicio.lembrete.Entity.Lembrete;
import com.example.exercicio.lembrete.Entity.Pessoa;
import com.example.exercicio.lembrete.Repository.LembreteRepository;
import com.example.exercicio.lembrete.Repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LembreteService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LembreteRepository lembreteRepository;

    @Transactional(rollbackFor = Exception.class)
    public void cadastrarLembrete(Long pessoaId, String lembreteTexto) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId).orElse(null);
        if (pessoa != null) {
            Lembrete lembrete = new Lembrete();
            lembrete.setLembrete(lembreteTexto);
            lembrete.setPessoa(pessoa);

            lembreteRepository.save(lembrete);
        } else {
            throw new IllegalArgumentException("Pessoa não encontrada com o ID fornecido.");
        }
    }
}
