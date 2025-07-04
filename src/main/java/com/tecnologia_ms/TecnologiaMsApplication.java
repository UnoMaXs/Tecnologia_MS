package com.tecnologia_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories
@SpringBootApplication
public class TecnologiaMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TecnologiaMsApplication.class, args);
    }

}
