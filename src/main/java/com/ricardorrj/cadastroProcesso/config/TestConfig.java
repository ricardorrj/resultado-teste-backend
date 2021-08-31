package com.ricardorrj.cadastroProcesso.config;

import com.ricardorrj.cadastroProcesso.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DbService service;

    @Bean
    public void init(){
        service.initDataBase();
    }

}
