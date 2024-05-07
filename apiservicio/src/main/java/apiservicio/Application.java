package apiservicio;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.webmvc.api.OpenApiActuatorResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public OpenAPI customOpenApi(){
		return new OpenAPI().info(new Info()
				.title("Servicio 1 Spring Boot API")
				.version("v1")
				.description("API para generar propuestas de fusion de comunidades")
				.termsOfService("https://swagger.io/terms")
				.license(new License().name("Apache 2.0").url("https://springdoc.org")));
	}
}
