package com.ricardorrj.cadastroProcesso.resources;

import com.ricardorrj.cadastroProcesso.domain.Pessoa;
import com.ricardorrj.cadastroProcesso.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@Controller
@RequestMapping(value="/pessoa")
@CrossOrigin("*")
public class PessoaResource {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping(value="{id}")
    @Transactional
    public ResponseEntity<Pessoa> findById(@PathVariable Long id){
        Pessoa pessoa = pessoaService.findById(id);
        return ResponseEntity.ok().body(pessoa);
    }


    @GetMapping
    @Transactional
    public ResponseEntity<Page<Pessoa>> findAll(Pageable pageable){

        Page<Pessoa> listPessoa = pessoaService.findAll(pageable);
        return ResponseEntity.ok().body(listPessoa);
    }


    @PostMapping
    @Transactional
    public ResponseEntity<Pessoa> create(@RequestBody @Valid Pessoa pessoa){
        pessoa = pessoaService.create(pessoa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoa.getId()).toUri();

        return ResponseEntity.created(uri).body(pessoa);
    }


    @PutMapping(value = "{id}")
    @Transactional
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody @Valid Pessoa pessoa){
        pessoa = pessoaService.update(id, pessoa);

        return ResponseEntity.ok().body(pessoa);
    }


    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        pessoaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
