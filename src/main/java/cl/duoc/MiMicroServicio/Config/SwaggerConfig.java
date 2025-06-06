package cl.duoc.MiMicroServicio.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(
            new Info()
            .title("Mi primer proyecto de Microservicios en DUOC 2025")
            .version("1.0")
            .description("Documentación de la API de Ventas")
        );
    }
}
