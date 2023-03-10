package com.douglasddr.restwithspringbootandjavaerudio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI custonOpenAPI() {
        return new OpenAPI()
        			.info(new Info()
        						.title("Restful Api with Java 18 and Spring boot 3")
        						.version("v1")
        						.description("Meu primeiro serviço restull")
        						.termsOfService("https://www.linkedin.com/in/douglas-dias-da-rocha-474876140/")
        						.license(new License()
        									.name("Apache 2.0")
        									.url("https://www.linkedin.com/in/douglas-dias-da-rocha-474876140/")));
    }
}
