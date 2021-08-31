package com.ricardorrj.cadastroProcesso.repository;

import com.ricardorrj.cadastroProcesso.domain.Pessoa;
import com.ricardorrj.cadastroProcesso.repositories.PessoaRepository;
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
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    private Pessoa cenarioTestes(){
        Pessoa pessoa = new Pessoa(null, "Rocky Balboa", "88129054809", LocalDate.parse("1970-12-25"));
        return pessoa;
    }


    @Test
    public void createTest() throws Exception{

        Pessoa pessoaRetorno = pessoaRepository.save(cenarioTestes());
        assertThat(pessoaRetorno.getNome()).isEqualTo("Rocky Balboa");
        assertThat(pessoaRetorno.getCpf()).isEqualTo("88129054809");
        assertThat(pessoaRetorno.getDataNascimento()).isEqualTo("1970-12-25");
    }

    @Test
    public void findByIdTest() throws Exception{

        pessoaRepository.save(cenarioTestes());
        Pessoa pessoa = pessoaRepository.findByCpf("88129054809");

        Optional<Pessoa> pessoaRetorno = pessoaRepository.findById(pessoa.getId());

        assertThat(pessoaRetorno.get().getNome()).isEqualTo("Rocky Balboa");
        assertThat(pessoaRetorno.get().getCpf()).isEqualTo("88129054809");
        assertThat(pessoaRetorno.get().getDataNascimento()).isEqualTo("1970-12-25");
    }


    @Test
    public void findByAllTest() throws Exception{
        Pageable pageable = PageRequest.of(0, 5);
        pessoaRepository.save(cenarioTestes());

        Page<Pessoa> listRetorno = pessoaRepository.findAll(pageable);
        assertThat(listRetorno.getContent().get(0).getNome()).isEqualTo("Rocky Balboa");
        assertThat(listRetorno.getContent().get(0).getCpf()).isEqualTo("88129054809");
        assertThat(listRetorno.getContent().get(0).getDataNascimento()).isEqualTo("1970-12-25");
    }


    @Test
    public void deleteTest() throws Exception{
        pessoaRepository.save(cenarioTestes());
        Pessoa pessoa = pessoaRepository.findByCpf("88129054809");

        pessoaRepository.deleteById(pessoa.getId());
    }
}
