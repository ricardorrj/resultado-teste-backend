package com.ricardorrj.cadastroProcesso.resources;

import com.ricardorrj.cadastroProcesso.domain.Processo;
import com.ricardorrj.cadastroProcesso.dto.ProcessoDTO;
import com.ricardorrj.cadastroProcesso.service.ProcessoService;
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
@RequestMapping(value="/processo")
@CrossOrigin("*")
public class ProcessoResource {

    @Autowired
    private ProcessoService processoService;

    @GetMapping(value="{id}")
    @Transactional
    public ResponseEntity<Processo> findById(@PathVariable Long id){
        Processo processo = processoService.findById(id);
        return ResponseEntity.ok().body(processo);
    }

    @GetMapping
    @Transactional
    public ResponseEntity<Page<ProcessoDTO>> findAll(Pageable pageable){
        Page<Processo> listProcesso = processoService.findAll(pageable);
        Page<ProcessoDTO> listProcessoDTO = listProcesso.map(obj -> new ProcessoDTO(obj));

        return ResponseEntity.ok().body(listProcessoDTO);
    }


    @PostMapping
    @Transactional
    public ResponseEntity<Processo> create(@RequestParam(value = "pessoa") Long idPessoa,
                                           @RequestBody @Valid Processo processo){
        processo = processoService.create(idPessoa, processo);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/processo/{id}").buildAndExpand(processo.getId()).toUri();

        return ResponseEntity.created(uri).body(processo);
    }


    @DeleteMapping(value = "{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id){
        processoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
