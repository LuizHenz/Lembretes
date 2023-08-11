package com.example.exercicio.lembrete.Repository;

import com.example.exercicio.lembrete.Entity.Lembrete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LembreteRepository extends JpaRepository<Lembrete, Long> {
}
