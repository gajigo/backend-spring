package br.com.uniamerica.gajigo.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPI30
public class SwaggerConfigurer {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("gajigo-api")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Gajigo API")
                    .description("Gajigo é uma plataforma de gestão de eventos inteligente")
                    .version("v0.0.1")
                    .license(new License().name("Comercial"))
                ).externalDocs(new ExternalDocumentation()
                    .description("Repositório")
                    .url("https://github.com/gajigo"));
    }
}
