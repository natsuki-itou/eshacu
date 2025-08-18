package dev.eshacu.config;

import  lombok.Data;
import  org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "cors")
public class CorsProps {
    private List<String> allowedOrigins = List.of();
    private List<String> allowedMethods = List.of("GET", "POST", "HEAD", "PUT", "PATCH", "DELETE", "OPTIONS");
    private List<String> allowedHeaders = List.of("*");
    private List<String> exposedHeaders = List.of(); // Location, Authorization など必要なら
    private Boolean allowCredentials = true;
    private Long maxAge = 3600L;
    private List<String> pathPatterns = List.of("/api/**");
}

