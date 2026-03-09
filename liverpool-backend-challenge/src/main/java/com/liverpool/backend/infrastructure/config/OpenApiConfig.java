package com.liverpool.backend.infrastructure.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Liverpool Backend Challenge").version("v1")
                        .description("API para gestión de clientes y búsqueda de órdenes")
                        .contact(new Contact().name("Ray"))
                        .license(new License().name("Internal Use")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project documentation"));
    }
}
