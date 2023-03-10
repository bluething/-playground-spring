package io.github.bluething.playground.spring.gs.uploading.files;

import io.github.bluething.playground.spring.gs.uploading.files.file.Storage;
import io.github.bluething.playground.spring.gs.uploading.files.file.StorageProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class GsUploadingFilesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GsUploadingFilesApplication.class, args);
	}

	@Bean
	CommandLineRunner init(Storage storage) {
		return (args -> {
			storage.deleteAll();
			storage.init();
		});
	}

}
