package io.github.bluething.playground.spring.testcontainers.gettingstartedguideslocalstack_initializer;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.S3;
import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SQS;

class TestcontainersInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    static LocalStackContainer localStack = new LocalStackContainer(DockerImageName.parse("localstack/localstack:2.1.0"));

    static {
        Startables.deepStart(localStack).join();
    }
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        TestPropertyValues.of(
                "app.bucket=app-bucket",
                "app.queue=APP_QUEUE",
                "spring.cloud.aws.region.static=" + localStack.getRegion(),
                "spring.cloud.aws.credentials.access-key=" + localStack.getAccessKey(),
                "spring.cloud.aws.credentials.secret-key=" + localStack.getSecretKey(),
                "spring.cloud.aws.s3.endpoint=" + localStack.getEndpointOverride(S3).toString(),
                "spring.cloud.aws.sqs.endpoint=" + localStack.getEndpointOverride(SQS).toString()
        ).applyTo(applicationContext.getEnvironment());

        try {
            localStack.execInContainer("awslocal", "s3", "mb", "s3://app-bucket");
            localStack.execInContainer("awslocal", "sqs", "create-queue", "--queue-name", "APP_QUEUE");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
