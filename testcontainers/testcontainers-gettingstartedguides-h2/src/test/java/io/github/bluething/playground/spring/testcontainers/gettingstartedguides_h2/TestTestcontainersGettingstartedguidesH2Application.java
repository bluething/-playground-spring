package io.github.bluething.playground.spring.testcontainers.gettingstartedguides_h2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestTestcontainersGettingstartedguidesH2Application {

	@Bean
	@ServiceConnection
	PostgreSQLContainer<?> postgresContainer() {
		return new PostgreSQLContainer<>("postgres:latest");
	}

	public static void main(String[] args) {
		SpringApplication.from(TestcontainersGettingstartedguidesH2Application::main).with(TestTestcontainersGettingstartedguidesH2Application.class).run(args);
	}

}
