package com.demo.embeddedpostgreswithmybatis.mapper;

import com.demo.embeddedpostgreswithmybatis.domain.TestTable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ExampleMapperTest {

    @Autowired
    private ExampleMapper exampleMapper;

    @Test
    void findById_returnsCorrectTestTable() {
        TestTable result = exampleMapper.findById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void findById_returnsNullWhenIdDoesNotExist() {
        TestTable result = exampleMapper.findById(999);
        assertNull(result);
    }

    @Test
    void findById_throwsExceptionWhenIdIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> exampleMapper.findById(-1));
    }
}