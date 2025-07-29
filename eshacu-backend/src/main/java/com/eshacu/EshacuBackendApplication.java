package com.eshacu;

import com.eshacu.config.CorsProps;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EshacuBackendApplication implements CommandLineRunner {

    private final CorsProps corsProps;

    public EshacuBackendApplication(CorsProps corsProps) {
        this.corsProps = corsProps;
    }

    public static void main(String[] args) {
        SpringApplication.run(EshacuBackendApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("CORS allowed-origins = " + corsProps.getAllowedOrigins());
    }
}

