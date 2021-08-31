package com.ricardorrj.cadastroProcesso.repository;

import com.ricardorrj.cadastroProcesso.domain.Pessoa;
import com.ricardorrj.cadastroProcesso.domain.Processo;
import com.ricardorrj.cadastroProcesso.repositories.PessoaRepository;
import com.ricardorrj.cadastroProcesso.repositories.ProcessoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@DataJpaTest
@ActiveProfiles("test")
public class ProcessoRepositoryTest {

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;


    private Processo cenarioTestes(){
        Processo processo = new Processo(1L, 55321531L, 2021, null);
        return processo;
    }


    @Test
    public void createTest() throws Exception{
        Pessoa pessoaCenario = pessoaRepository.save(new Pessoa(1L, "Rocky Balboa", "88129054809", LocalDate.parse("1970-12-25")));

        Processo processo = cenarioTestes();
        processo.setPessoa(pessoaCenario);

        Processo processoRetorno = processoRepository.save(processo);
        assertThat(processoRetorno.getNumero()).isEqualTo(55321531L);
        assertThat(processoRetorno.getAno()).isEqualTo(2021);
    }

    @Test
    public void findByIdTest() throws Exception{
        Pessoa pessoaCenario = pessoaRepository.save(new Pessoa(1L, "Rocky Balboa", "88129054809", LocalDate.parse("1970-12-25")));

        Processo processo = cenarioTestes();
        processo.setPessoa(pessoaCenario);
        processo = processoRepository.save(processo);

        Optional<Processo> processoRetorno = processoRepository.findById(processo.getId());
        assertThat(processoRetorno.get().getNumero()).isEqualTo(55321531L);
        assertThat(processoRetorno.get().getAno()).isEqualTo(2021);
    }


    @Test
    public void findByAllTest() throws Exception{
        Pageable pageable = PageRequest.of(0, 5);
        Pessoa pessoaCenario = pessoaRepository.save(new Pessoa(1L, "Rocky Balboa", "88129054809", LocalDate.parse("1970-12-25")));

        Processo processo = cenarioTestes();
        processo.setPessoa(pessoaCenario);
        processo = processoRepository.save(processo);

        Page<Processo> listRetorno = processoRepository.findAll(pageable);
        assertThat(listRetorno.getContent().get(0).getNumero()).isEqualTo(55321531L);
        assertThat(listRetorno.getContent().get(0).getAno()).isEqualTo(2021);
    }


    @Test
    public void deleteTest() throws Exception{
        Pessoa pessoaCenario = pessoaRepository.save(new Pessoa(1L, "Rocky Balboa", "88129054809", LocalDate.parse("1970-12-25")));

        Processo processo = cenarioTestes();
        processo.setPessoa(pessoaCenario);
        processo = processoRepository.save(processo);

        processoRepository.deleteById(processo.getId());
    }
}