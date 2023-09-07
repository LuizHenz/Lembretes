package com.example.exercicio.lembrete;

import com.example.exercicio.lembrete.Controller.LembreteController;
import com.example.exercicio.lembrete.Controller.PessoaController;
import com.example.exercicio.lembrete.DTO.LembretesDTO;
import com.example.exercicio.lembrete.DTO.PessoaDTO;
import com.example.exercicio.lembrete.Entity.Lembrete;
import com.example.exercicio.lembrete.Entity.Pessoa;
import com.example.exercicio.lembrete.Repository.PessoaRepository;
import com.example.exercicio.lembrete.Service.PessoaService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class PessoaTest {

    @MockBean
    PessoaRepository pessoaRepository;

    @Autowired
    private PessoaController pessoaController;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private LembreteController lembreteController;


    @BeforeEach
    void injectData() {

        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa(1L, "Luiz"));
        Pessoa pessoaCreate = new Pessoa(1L,"Roberto");

        List<Lembrete> lembretes = new ArrayList<>();
        lembretes.add(new Lembrete( 1L,"lembrete de teste",pessoas.get(0)));


        Mockito.when(pessoaRepository.findByNome("Luiz")).thenReturn(pessoas);
        Mockito.when(pessoaRepository.save(pessoaCreate)).thenReturn(pessoaCreate);

    }

    @Test
    void testControllerPessoa(){
        var nomeDump = pessoaController.findByNome("Luiz");
        Assert.assertEquals("Luiz", nomeDump.getBody());
    }

    @Test
    void testeControllerPessoaFindByNome() {
        var d = pessoaService.findByNome("Luiz");
        //System.out.println(d.get(0).getNome());
        Assert.assertEquals("Luiz",d.get(0).getNome());
    }

    //____________________________________________________

    @Test
    void testeControllerPessoaEditar(){
        var dump = pessoaController.editarPessoa(1L,new PessoaDTO(1L, "Roberto"));

        //var nome = pessoaController.findByNome("Roberto");

        Assert.assertEquals("Editado com sucesso", dump.getBody());

    }

    @Test
    void testeControllerPessoaCadastro(){
        var pessoaDumpCreate = pessoaController.cadastrarPessoa(new PessoaDTO(1L, "Roberto"));

        Assert.assertEquals("Pessoa cadastrada", pessoaDumpCreate.getBody());
    }

    @Test
    void testeControllerPessoaDeletar(){
        var pessoaDumpDelete = pessoaController.deletarPessoa(1L);

        Assert.assertEquals("Objeto deletado",pessoaDumpDelete.getBody());
    }

    @Test
    void testeControllerLembretesCadastrar(){
        var pessoaDumpCadastro =  lembreteController.cadastrarLembrete(new LembretesDTO(1L, "Lembrete",new Pessoa(1L,"Roberto")));

        Assert.assertEquals("lembrete cadastrado", pessoaDumpCadastro.getBody());
    }


}
