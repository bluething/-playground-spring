package io.github.bluething.playground.spring.testcontainers.gettingstartedguides_h2_flyway;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@EnableTestcontainers
class ProductRepositoryPostgresWithInitializerTest {
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
    void whenExecuteWithTestcontainersPostgresThenProductRepositoryShouldSuccess() {
        productRepository.insertWithNativePostgres(100L, "code", "name");
        List<Product> products = productRepository.findAll();
        Assertions.assertEquals(3, products.size());
    }
}
