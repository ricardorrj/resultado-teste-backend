package com.ricardorrj.cadastroProcesso.service;

import com.ricardorrj.cadastroProcesso.domain.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DbService {

    @Autowired
    private PessoaService pessoaService;

    public void initDataBase(){

        Pessoa pessoa = pessoaService.create(new Pessoa(null, "Rocky Balboa", "88129054809", LocalDate.parse("1970-12-25")));
        Pessoa pessoa2 = pessoaService.create(new Pessoa(null, "Oliver Queen", "73331559008", LocalDate.parse("1985-09-09")));
        Pessoa pessoa3 = pessoaService.create(new Pessoa(null, "Barry Allen", "21741014670", LocalDate.parse("1989-09-09")));
    }

}
