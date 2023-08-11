package com.example.exercicio.lembrete.Service;

import com.example.exercicio.lembrete.Entity.Pessoa;
import com.example.exercicio.lembrete.Repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional(rollbackFor = Exception.class)
    public List<Pessoa> findByNome(String nome) {
        return pessoaRepository.findByNome(nome);
    }

    @Transactional(rollbackFor = Exception.class)
    public Pessoa buscarComLembretes(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id).orElse(null);

        if (pessoa != null) {
            pessoa.getLembretes().size();
            return pessoa;
        } else {
            throw new IllegalArgumentException("Pessoa não encontrada com o ID fornecido.");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void cadastro (final Pessoa pessoa){
        Assert.isTrue(pessoa.getNome() != null, "Erro, campo vazio.");
        this.pessoaRepository.save(pessoa);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editar (final Long id, final Pessoa pessoa){
        final Pessoa pessoaBanco = this.pessoaRepository.findById(id).orElse(null);

        Assert.isTrue(pessoaBanco == null || !pessoaBanco.getId().equals(pessoa.getId()), "Não foi possível identificar o cliente.");
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletar (final Long id){
        final Pessoa pessoaBanco = this.pessoaRepository.findById(id).orElse(null);
        Assert.isTrue(pessoaBanco != null, "Registro não encontrado.");
        this.pessoaRepository.delete(pessoaBanco);
    }
}
