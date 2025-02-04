package br.com.mgobo.web;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

public class BaseIntegratedTest {
    protected static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres"))
            .withDatabaseName("carsystem-db")
            .withUsername("sa")
            .withPassword("123");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", ()-> postgreSQLContainer.getJdbcUrl());
        registry.add("spring.datasource.username", ()-> postgreSQLContainer.getUsername());
        registry.add("spring.datasource.password", ()-> postgreSQLContainer.getPassword());
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "update");
    }
}
