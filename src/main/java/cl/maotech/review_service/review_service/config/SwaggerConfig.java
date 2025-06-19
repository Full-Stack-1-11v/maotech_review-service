package cl.maotech.review_service.review_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;

/**
 * Configuration class for Swagger/OpenAPI documentation.
 * This class sets up the OpenAPI documentation for the Review Service API.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Creates an OpenAPI bean for the Review Service API documentation.
     * This method configures the API title, version, and description.
     * @return an OpenAPI instance with the configured information
     */
     @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Review Service API")
                        .version("v1")
                        .description("API para manejar reseñas de productos"));
    }
}
