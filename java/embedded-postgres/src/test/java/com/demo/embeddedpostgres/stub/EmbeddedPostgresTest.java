package com.demo.embeddedpostgres.stub;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.Statement;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class EmbeddedPostgresTest {

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
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("test-init.sql");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String sql = reader.lines().collect(Collectors.joining("\n"));

            try (Statement s = connection.createStatement()) {
                s.execute(sql);
            }
        }
    }

    @Test
    @DisplayName("테스트 테이블 connection 확인")
    public void testEmbeddedPg() throws Exception {
        try (Statement s = connection.createStatement()) {
            var rs = s.executeQuery("SELECT 1");
            assertTrue(rs.next());
            assertEquals(1, rs.getInt(1));
            assertFalse(rs.next());
        }
    }

    @Test
    @DisplayName("테스트 테이블 데이터 조회 - 정상")
    public void findByIdForSuccess() throws Exception {
        try (Statement s = connection.createStatement()) {
            var rs = s.executeQuery("SELECT value FROM test_table WHERE id = 1");
            assertTrue(rs.next());
            assertEquals("A", rs.getString("value"));
            assertFalse(rs.next());
        }
    }

    @Test
    @DisplayName("테스트 테이블 데이터 조회 - 실패")
    public void findByIdForFailed() throws Exception {
        try (Statement s = connection.createStatement()) {
            var rs = s.executeQuery("SELECT value FROM test_table WHERE id = 2");
            assertFalse(rs.next());
        }
    }
}
