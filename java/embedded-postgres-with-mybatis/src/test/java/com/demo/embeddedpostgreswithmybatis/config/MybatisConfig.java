package com.demo.embeddedpostgreswithmybatis.config;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

@Configuration
@Profile("test")
public class MybatisConfig {

    @Bean
    public EmbeddedPostgres embeddedPostgres() throws IOException {
        return EmbeddedPostgres
                .builder()
                .setPort(54322)
                .start();
    }

    @Bean
    public DataSource dataSource(EmbeddedPostgres postgres) throws SQLException {
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver("org.postgresql.Driver");
        dataSource.setUrl(postgres.getJdbcUrl("postgres", "postgres"));
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");

        initializeDatabase(dataSource);

        return dataSource;
    }

    private void initializeDatabase(DataSource dataSource) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement stmt = connection.createStatement()) {

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("initialize.sql");
            if (inputStream == null) {
                throw new IllegalArgumentException("SQL 파일을 찾을 수 없습니다.");
            }
            String sql = new BufferedReader(new InputStreamReader(inputStream))
                    .lines().collect(Collectors.joining("\n"));

            for (String statement : sql.split(";")) {
                if (!statement.trim().isEmpty()) {
                    stmt.execute(statement);
                }
            }
        }
    }


}
