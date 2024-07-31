package com.demo.embeddedpostgres.helper;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.Statement;

@SpringBootTest
public class EmbeddedPostgresHelper {

    @TempDir
    public Path tf;

    private EmbeddedPostgres pg;
    private Connection connection;

    @BeforeEach
    public void setUp() throws Exception {
        pg = EmbeddedPostgres.builder()
                .setDataDirectory(Files.createDirectories(tf.resolve("data-dir-parent").resolve("data-dir")))
                .start();
        connection = pg.getPostgresDatabase().getConnection();
        initializeDatabase();
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
        if (pg != null) {
            pg.close();
        }
    }

    private void initializeDatabase() throws Exception {
        try (Statement s = connection.createStatement()) {
            // 테스트 할 sql 파일 작성
        }
    }

}
