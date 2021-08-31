package com.ricardorrj.cadastroProcesso.service;

import com.ricardorrj.cadastroProcesso.domain.Pessoa;
import com.ricardorrj.cadastroProcesso.domain.Processo;
import com.ricardorrj.cadastroProcesso.repositories.ProcessoRepository;
import com.ricardorrj.cadastroProcesso.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private PessoaService pessoaService;


    public Processo findById(Long id){
        Optional<Processo> processo = processoRepository.findById(id);
        return processo.orElseThrow(() -> new ObjectNotFoundException("Processo não encontrado!"));
    }


    public Page<Processo> findAll(Pageable pageable){
        return processoRepository.findAll(pageable);
    }


    public Processo create(Long idPessoa, Processo processo){
        processo.setId(null);

        Pessoa pessoa = pessoaService.findById(idPessoa);
        processo.setPessoa(pessoa);

        return processoRepository.save(processo);
    }


    public void delete(Long id){
        this.findById(id);
        processoRepository.deleteById(id);
    }

}
