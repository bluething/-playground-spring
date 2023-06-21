package com.example.testcontainers_gettingstartedguides_springboot;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class TestcontainersGettingstartedguidesSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestcontainersGettingstartedguidesSpringbootApplication.class, args);
	}

}

@Entity
@Table(name = "customers")
class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, unique = true)
	private String email;

	public Customer() {
	}

	public Customer(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

interface CustomerRepository extends JpaRepository<Customer, Long> {}

@RestController
class CustomerController {
	private final CustomerRepository repository;

	public CustomerController(CustomerRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/api/customers")
	public List<Customer> getAll() {
		return repository.findAll();
	}

}
