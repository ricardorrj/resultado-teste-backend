package com.ricardorrj.cadastroProcesso.service;

import com.ricardorrj.cadastroProcesso.CadastroProcessoApplication;
import com.ricardorrj.cadastroProcesso.domain.Pessoa;
import com.ricardorrj.cadastroProcesso.repositories.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = CadastroProcessoApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PessoaServiceTest {

    @Autowired
    private PessoaService pessoaService;

    @MockBean
    private PessoaRepository pessoaRepository;

    private Pessoa cenarioTestes(){
        Pessoa pessoa = new Pessoa(1L, "Rocky Balboa", "88129054809", LocalDate.parse("1970-12-25"));
        return pessoa;
    }


    @Test
    public void findByIdTest() throws Exception{

        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(cenarioTestes()));

        Pessoa pessoaRetorno = pessoaService.findById(1L);
        assertThat(pessoaRetorno.getNome()).isEqualTo("Rocky Balboa");
        assertThat(pessoaRetorno.getCpf()).isEqualTo("88129054809");
        assertThat(pessoaRetorno.getDataNascimento()).isEqualTo("1970-12-25");
    }


    @Test
    public void findByAllTest() throws Exception{
        Pageable pageable = PageRequest.of(0, 5);
        Pessoa data = cenarioTestes();

        when(pessoaRepository.findAll(pageable)).thenReturn(new PageImpl<>(Collections.singletonList(data)));

        Page<Pessoa> listRetorno = pessoaService.findAll(pageable);
        assertThat(listRetorno.getContent().get(0).getNome()).isEqualTo("Rocky Balboa");
        assertThat(listRetorno.getContent().get(0).getCpf()).isEqualTo("88129054809");
        assertThat(listRetorno.getContent().get(0).getDataNascimento()).isEqualTo("1970-12-25");
    }


    @Test
    public void createTest() throws Exception{

        when(pessoaRepository.save(any())).thenReturn(cenarioTestes());

        Pessoa pessoaRetorno = pessoaService.create(cenarioTestes());
        assertThat(pessoaRetorno.getNome()).isEqualTo("Rocky Balboa");
        assertThat(pessoaRetorno.getCpf()).isEqualTo("88129054809");
        assertThat(pessoaRetorno.getDataNascimento()).isEqualTo("1970-12-25");
    }


    @Test
    public void updateTest() throws Exception{
        Pessoa cenario = cenarioTestes();
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(cenario));
        when(pessoaRepository.save(any())).thenReturn(cenario);

        Pessoa pessoaRetorno = pessoaService.create(cenarioTestes());
        assertThat(pessoaRetorno.getNome()).isEqualTo("Rocky Balboa");
        assertThat(pessoaRetorno.getCpf()).isEqualTo("88129054809");
        assertThat(pessoaRetorno.getDataNascimento()).isEqualTo("1970-12-25");
    }


    @Test
    public void deleteTest() throws Exception{
        Pessoa cenario = cenarioTestes();

        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(cenario));

        pessoaService.delete(1L);
    }

}
