package com.drew.Bank_App;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Bank App!!",
				description = "Rest APIs for bank app",
				version = "v1",
				contact = @Contact(
						name = "D",
						email = "a@gmail.com"
				),
				license = @License(
						name = "D"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Hello World! Welcome to my Bank App's documentation.",
				url = "https://github.com/bighorse0/Bank-Application"
		)
)
public class BankAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAppApplication.class, args);
	}

}
