package com.demo.config;

import org.springframework.context.annotation.*;
import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI springBalanceServiceOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Balance Service API")
						.description("Balance Service"))
				.externalDocs( new ExternalDocumentation()
						.description("")
						.url(""));
				
	}

}
