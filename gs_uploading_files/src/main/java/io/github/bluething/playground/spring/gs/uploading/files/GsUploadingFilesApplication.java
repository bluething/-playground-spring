package io.github.bluething.playground.spring.gs.uploading.files;

import io.github.bluething.playground.spring.gs.uploading.files.file.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class GsUploadingFilesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GsUploadingFilesApplication.class, args);
	}

}
