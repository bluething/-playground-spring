package io.github.bluething.playground.spring.testcontainers.gettingstartedguides_mockserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

@SpringBootApplication
public class TestcontainersGettingstartedguidesMockserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestcontainersGettingstartedguidesMockserverApplication.class, args);
	}

}

@RestController
@RequestMapping("/api")
class AlbumController {
	private static final Logger logger = LoggerFactory.getLogger(AlbumController.class);

	private final PhotoServiceClient photoServiceClient;

	AlbumController(PhotoServiceClient photoServiceClient) {
		this.photoServiceClient = photoServiceClient;
	}

	@GetMapping("/albums/{albumId}")
	public ResponseEntity<Album> getAlbumById(@PathVariable Long albumId) {
		try {
			List<Photo> photos = photoServiceClient.getPhotos(albumId);
			return ResponseEntity.ok(new Album(albumId, photos));
		} catch (WebClientResponseException e) {
			logger.error("Failed to get photos", e);
			return new ResponseEntity<>(e.getStatusCode());
		}
	}
}

@Configuration
class AppConfig {
	@Bean
	public PhotoServiceClient photoServiceClient(
			@Value("${photos.api.base-url}") String photosApiBaseUrl
	) {
		WebClient client = WebClient.builder().baseUrl(photosApiBaseUrl).build();
		HttpServiceProxyFactory factory = HttpServiceProxyFactory
				.builder(WebClientAdapter.forClient(client))
				.build();
		return factory.createClient(PhotoServiceClient.class);
	}
}

interface PhotoServiceClient {
	@GetExchange("/albums/{albumId}/photos")
	List<Photo> getPhotos(@PathVariable Long albumId);
}
