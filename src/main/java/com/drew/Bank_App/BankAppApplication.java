package com.drew.Bank_App;

import io.github.cdimascio.dotenv.Dotenv;
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
		// Load .env file
		Dotenv dotenv = Dotenv.load();

		// Set environment variables
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		System.setProperty("EMAIL", dotenv.get("EMAIL"));
		System.setProperty("EMAIL_PASSWORD", dotenv.get("EMAIL_PASSWORD"));
		System.setProperty("SECRET_KEY", dotenv.get("SECRET_KEY"));

		SpringApplication.run(BankAppApplication.class, args);
	}

}
