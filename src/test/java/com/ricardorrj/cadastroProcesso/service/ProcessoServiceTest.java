package com.ricardorrj.cadastroProcesso.service;

import com.ricardorrj.cadastroProcesso.CadastroProcessoApplication;
import com.ricardorrj.cadastroProcesso.domain.Pessoa;
import com.ricardorrj.cadastroProcesso.domain.Processo;
import com.ricardorrj.cadastroProcesso.repositories.PessoaRepository;
import com.ricardorrj.cadastroProcesso.repositories.ProcessoRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CadastroProcessoApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProcessoServiceTest {

    @Autowired
    private ProcessoService processoService;

    @MockBean
    private PessoaRepository pessoaRepository;

    @MockBean
    private ProcessoRepository processoRepository;


    private Processo cenarioTestes(){
        Pessoa pessoa = new Pessoa(1L, "Rocky Balboa", "88129054809", LocalDate.parse("1970-12-25"));
        Processo processo = new Processo(1L, 55321531L, 2021, pessoa);

        return processo;
    }


    @Test
    public void findByIdTest() throws Exception{

        when(processoRepository.findById(1L)).thenReturn(Optional.of(cenarioTestes()));

        Processo processoRetorno = processoService.findById(1L);
        assertThat(processoRetorno.getId()).isEqualTo(1);
        assertThat(processoRetorno.getNumero()).isEqualTo(55321531L);
        assertThat(processoRetorno.getAno()).isEqualTo(2021);
    }


    @Test
    public void findByAllTest() throws Exception{

        Pageable pageable = PageRequest.of(0, 5);
        Processo data = cenarioTestes();
        when(processoRepository.findAll(pageable)).thenReturn(new PageImpl<>(Collections.singletonList(data)));

        Page<Processo> listRetorno = processoService.findAll(pageable);
        assertThat(listRetorno.getContent().get(0).getId()).isEqualTo(1);
        assertThat(listRetorno.getContent().get(0).getNumero()).isEqualTo(55321531L);
        assertThat(listRetorno.getContent().get(0).getAno()).isEqualTo(2021);
    }


    @Test
    public void createTest() throws Exception{
        Processo cenario = cenarioTestes();
        Pessoa pessoa = new Pessoa(1L, "Rocky Balboa", "88129054809", LocalDate.parse("1970-12-25"));

        when(processoRepository.save(any())).thenReturn(cenario);
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        Processo processoRetorno = processoService.create(pessoa.getId(), cenario);
        assertThat(processoRetorno.getNumero()).isEqualTo(55321531L);
        assertThat(processoRetorno.getAno()).isEqualTo(2021);
    }


    @Test
    public void deleteTest() throws Exception{
        Processo cenario = cenarioTestes();

        when(processoRepository.findById(1L)).thenReturn(Optional.of(cenario));
        processoService.delete(1L);
    }
}
