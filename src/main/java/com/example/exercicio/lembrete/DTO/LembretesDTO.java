package com.example.exercicio.lembrete.DTO;

import com.example.exercicio.lembrete.Entity.Pessoa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LembretesDTO {
    private Long id;

    private String lembrete;

    private Pessoa pessoa;

    public  LembretesDTO(){}

    public LembretesDTO(Long id, String lembrete, Pessoa pessoa) {
        this.id=id;
        this.lembrete = lembrete;
        this.pessoa = pessoa;
    }
}