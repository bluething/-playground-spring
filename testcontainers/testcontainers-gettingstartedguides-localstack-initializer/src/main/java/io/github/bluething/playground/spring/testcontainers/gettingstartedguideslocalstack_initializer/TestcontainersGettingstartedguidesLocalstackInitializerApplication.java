package io.github.bluething.playground.spring.testcontainers.gettingstartedguideslocalstack_initializer;

import io.awspring.cloud.s3.S3Template;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@ConfigurationPropertiesScan
@SpringBootApplication
public class TestcontainersGettingstartedguidesLocalstackInitializerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestcontainersGettingstartedguidesLocalstackInitializerApplication.class, args);
	}

}

@Service
class StorageService {
	private final S3Template s3Template;

	public StorageService(S3Template s3Template) {
		this.s3Template = s3Template;
	}

	public void upload(String bucketName, String key, InputStream stream) {
		this.s3Template.upload(bucketName, key, stream);
	}

	public InputStream download(String bucketName, String key) throws IOException {
		return this.s3Template.download(bucketName, key).getInputStream();
	}

	public String downloadAsString(String bucketName, String key) throws IOException {
		try (InputStream is = this.download(bucketName, key)) {
			return new String(is.readAllBytes());
		}
	}
}

@Service
class MessageSender {
	private final SqsTemplate sqsTemplate;

	public MessageSender(SqsTemplate sqsTemplate) {
		this.sqsTemplate = sqsTemplate;
	}

	public void publish(String queueName, Object payload) {
		sqsTemplate.send(to -> to.queue(queueName).payload(payload));
	}
}

@Service
class MessageListener {
	private final S3Template s3Template;
	private final ApplicationProperties properties;

	public MessageListener(S3Template s3Template, ApplicationProperties properties) {
		this.s3Template = s3Template;
		this.properties = properties;
	}

	@SqsListener(queueNames = {"${app.queue}"})
	public void handle(Message message) {
		String bucketName = this.properties.bucket();
		String key = message.uuid().toString();
		ByteArrayInputStream is = new ByteArrayInputStream(message.content().getBytes(StandardCharsets.UTF_8));
		this.s3Template.upload(bucketName, key, is);
	}
}
