package io.github.bluething.playground.spring.scheduler.yeralin.priorityjob.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

@Configuration
class SchedulerConfig {

    @Bean
    TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor() {
            @Override
            protected BlockingQueue<Runnable> createQueue(int queueCapacity) {
                return new PriorityBlockingQueue<>(3);
            }
        };
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(3);
        return executor;
    }
}
