package io.github.bluething.playground.spring.callicoderuploaddownloadrest;

import io.github.bluething.playground.spring.callicoderuploaddownloadrest.file.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class CallicoderUploadDownloadRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CallicoderUploadDownloadRestApplication.class, args);
	}

}
