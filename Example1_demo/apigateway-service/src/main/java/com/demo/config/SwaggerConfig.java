package com.demo.config;

import org.springframework.context.annotation.*;
import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI springBalanceServiceOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("APIGateway Service API")
						.description("APIGateway Service"))
				.externalDocs( new ExternalDocumentation()
						.description("")
						.url(""));
				
	}

}
