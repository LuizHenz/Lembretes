package com.example.exercicio.lembrete.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO {
    private Long id;
    private String nome;

    public PessoaDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}
