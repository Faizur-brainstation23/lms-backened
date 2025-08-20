package com.library.management.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Library Management System API")
                        .description("A comprehensive REST API for managing library operations including books, categories, users, and borrowing transactions with JWT Authentication.\n\n" +
                                "**How to use JWT Authentication:**\n" +
                                "1. First, register a new user using the `/api/auth/register` endpoint\n" +
                                "2. Then, login using the `/api/auth/login` endpoint to get your JWT token\n" +
                                "3. Copy the token from the response\n" +
                                "4. Click the 'Authorize' button above and paste the token (without 'Bearer ' prefix)\n" +
                                "5. Now you can test all protected endpoints!\n\n" +
                                "**Test Credentials:**\n" +
                                "You can create your own account or use these sample credentials after registering:\n" +
                                "- Username: testuser\n" +
                                "- Password: password123")
                        .version("2.0")
                        .contact(new Contact()
                                .name("Library Management Team")
                                .email("support@librarymanagement.com")
                                .url("https://librarymanagement.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Development server"),
                        new Server()
                                .url("https://api.librarymanagement.com")
                                .description("Production server")
                ))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Enter JWT token obtained from login endpoint. Do not include 'Bearer ' prefix.")));
    }
}

