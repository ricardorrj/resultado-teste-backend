package com.ricardorrj.cadastroProcesso.config;

import com.ricardorrj.cadastroProcesso.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DbService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean init() {
        if(strategy.equals("create")) {
            dbService.initDataBase();
        }

        return false;
    }
}
