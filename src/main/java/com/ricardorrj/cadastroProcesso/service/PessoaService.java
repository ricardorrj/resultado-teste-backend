package com.ricardorrj.cadastroProcesso.service;

import com.ricardorrj.cadastroProcesso.domain.Pessoa;
import com.ricardorrj.cadastroProcesso.repositories.PessoaRepository;
import com.ricardorrj.cadastroProcesso.service.exception.CadastroException;
import com.ricardorrj.cadastroProcesso.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;


    public Pessoa findById(Long id){
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa.orElseThrow(() -> new ObjectNotFoundException("Cadastro não encontrado!"));
    }


    public Page<Pessoa> findAll(Pageable pageable){
        return pessoaRepository.findAll(pageable);
    }


    public Pessoa create(Pessoa pessoa){
        pessoa.setId(null);
        this.validaRegra(pessoa);

        return pessoaRepository.save(pessoa);
    }


    public Pessoa update(Long id, Pessoa pessoa){
        pessoa.setId(null);

        Pessoa newPessoa = this.findById(id);
        newPessoa.setNome(pessoa.getNome());
        newPessoa.setDataNascimento(pessoa.getDataNascimento());

        return pessoaRepository.save(newPessoa);
    }


    public void delete(Long id){
        this.findById(id);

        try {
            pessoaRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new com.ricardorrj.cadastroProcesso.service.exception.DataIntegrityViolationException("Este cadastro está vinculado a um processo, " +
                                                                                                                    "por tanto não pode ser deletado!");
        }
    }
    
    
    private void validaRegra(Pessoa obj){
        if(pessoaRepository.findByCpf(obj.getCpf()) != null){
            throw new CadastroException("Já existe um cadastro com este CPF, cadastro não realizado!");
        }
    }


}
