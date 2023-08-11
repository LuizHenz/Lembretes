package com.example.exercicio.lembrete.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lembretes", schema = "public")
@Getter
@Setter
public class Lembrete {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "lembrete", nullable = false)
    private String lembrete;
    @JsonBackReference // Use esta anotação para evitar a serialização cíclica
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @PrePersist
    public void prePersist() {
        if (pessoa != null) {
            pessoa.getLembretes().add(this); // Adiciona o lembrete à lista de lembretes da pessoa
        }
    }
}
