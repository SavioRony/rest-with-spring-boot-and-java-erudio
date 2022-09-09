package br.com.erudio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("REST API's RESTFul do 0 à AWS c. Spring Boot 3 Java e Docker")
                        .version("v1")
                        .description("Some description about your API")
                        .termsOfService("https://github.com/SavioRony/rest-with-spring-boot-and-java-erudio")
                        .license(new License().name("Apache 2.0")
                                .url("https://github.com/SavioRony/rest-with-spring-boot-and-java-erudio")));
    }
}
