package com.example.exercicio.lembrete.Repository;

import com.example.exercicio.lembrete.Entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    @Query("SELECT p FROM Pessoa p WHERE p.nome = :nome")
    public List<Pessoa> findByNome(String nome);
}
