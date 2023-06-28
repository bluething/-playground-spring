package io.github.bluething.playground.spring.testcontainers.gettingstartedguides_h2_flyway;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@SpringBootApplication
public class TestcontainersGettingstartedguidesH2FlywayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestcontainersGettingstartedguidesH2FlywayApplication.class, args);
	}

}

interface ProductRepository extends JpaRepository<Product, Long> {
	@Modifying
	@Query(value = "INSERT INTO products(id, code, name) VALUES(:id, :code, :name) ON CONFLICT DO NOTHING;", nativeQuery = true)
	void insertWithNativePostgres(@Param("id") long id, @Param("code") String code, @Param("name") String name);
}

@Entity
@Table(name = "products")
class Product {
	@Id
	private Long id;

	@Column(nullable = false, unique = true)
	private String code;

	@Column(nullable = false)
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
