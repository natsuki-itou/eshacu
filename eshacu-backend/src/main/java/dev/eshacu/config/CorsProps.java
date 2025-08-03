package dev.eshacu.config;

import  lombok.Data;
import  org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "cors")
public class CorsProps {
    private  List<String> allowedOrigins = List.of();
}

