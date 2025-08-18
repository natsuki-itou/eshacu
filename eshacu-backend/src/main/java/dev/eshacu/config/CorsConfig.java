package dev.eshacu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableConfigurationProperties(CorsProps.class)
public class CorsConfig implements WebMvcConfigurer{

    private final CorsProps props;

    public CorsConfig(CorsProps props){
        this.props = props;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        var mapping = registry.addMapping(
                props.getPathPatterns().isEmpty() ? "/**" : props.getPathPatterns().get(0)
        );
        mapping
                .allowedOrigins(props.getAllowedOrigins().toArray(new String[0]))
                .allowedMethods(props.getAllowedMethods().toArray(new String[0]))
                .allowedHeaders(props.getAllowedHeaders().toArray(new String[0]))
                .allowCredentials(Boolean.TRUE.equals(props.getAllowCredentials()))
                .maxAge(props.getMaxAge());
        if (!props.getExposedHeaders().isEmpty()) {
            mapping.exposedHeaders(props.getExposedHeaders().toArray(new String[0]));
        }
    }
}
