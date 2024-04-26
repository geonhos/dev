package com.example.docker.jpa.env;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MariaDBContainer;

import static org.assertj.core.api.Assertions.assertThat;

public class MariaDBTestEnv extends IntegrateTestEnv {

    private static final String MARIA_DB_IMAGE = "mariadb:10.11.7";

    static MariaDBContainer mariadb = new MariaDBContainer<>(MARIA_DB_IMAGE)
            .withDatabaseName("my")
            .withUsername("user")
            .withPassword("password");

    static {
        mariadb.start();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mariadb::getJdbcUrl);
        registry.add("spring.datasource.username", mariadb::getUsername);
        registry.add("spring.datasource.password", mariadb::getPassword);
    }

    @Test
    @DisplayName("DB 실행 테스트")
    void test() {
        assertThat(mariadb.isRunning()).isTrue();
    }

}