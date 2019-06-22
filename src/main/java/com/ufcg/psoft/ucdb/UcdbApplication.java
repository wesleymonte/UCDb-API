package com.ufcg.psoft.ucdb;

import com.ufcg.psoft.ucdb.core.UcdbController;
import java.util.Properties;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;

@SpringBootApplication
public class UcdbApplication {

    @Bean
    @Lazy
    public UcdbController ucdbController(){
        UcdbController ucdbController = new UcdbController();
        return ucdbController;
    }

    public static void main(String[] args) {
        SpringApplication.run(UcdbApplication.class, args);
    }

}
