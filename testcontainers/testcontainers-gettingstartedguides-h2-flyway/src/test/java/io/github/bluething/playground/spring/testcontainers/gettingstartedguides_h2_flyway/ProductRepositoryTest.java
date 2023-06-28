package io.github.bluething.playground.spring.testcontainers.gettingstartedguides_h2_flyway;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    @Sql("classpath:/sql/seed-data.sql")
    void shouldGetAllProducts() {
        List<Product> products = productRepository.findAll();
        Assertions.assertEquals(2, products.size());
    }

    @Test
    @Sql("classpath:/sql/seed-data.sql")
    void whenExecuteWithH2ThenProductRepositoryShouldThrowInvalidDataAccessResourceUsageException() {
        Throwable exception = assertThrows(InvalidDataAccessResourceUsageException.class, () -> productRepository.insertWithNativePostgres(100L, "code", "name"));
        String exceptionMessage = """
               could not prepare statement [Syntax error in SQL statement "INSERT INTO products(id, code, name) VALUES(?, ?, ?) [*]ON CONFLICT DO NOTHING;"; SQL statement:
               INSERT INTO products(id, code, name) VALUES(?, ?, ?) ON CONFLICT DO NOTHING; [42000-214]] [INSERT INTO products(id, code, name) VALUES(?, ?, ?) ON CONFLICT DO NOTHING;]; SQL [INSERT INTO products(id, code, name) VALUES(?, ?, ?) ON CONFLICT DO NOTHING;]
               """;
        Assertions.assertEquals(exceptionMessage.trim(), exception.getMessage());
    }
}
