package com.example.exercicio.lembrete.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "pessoa", schema = "public")
@Getter
@Setter
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @JsonIgnore
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Lembrete> lembretes;


    public Pessoa() {};

    public Pessoa(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
