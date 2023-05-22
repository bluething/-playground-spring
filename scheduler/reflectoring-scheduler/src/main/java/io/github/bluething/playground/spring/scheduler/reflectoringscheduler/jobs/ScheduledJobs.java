package io.github.bluething.playground.spring.scheduler.reflectoringscheduler.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
class ScheduledJobs {
    private static final Logger LOG = LoggerFactory.getLogger(ScheduledJobs.class);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedDelay = 2000)
    void reportCurrentTimeFixedDelay() {
        LOG.info("reportCurrentTimeFixedDelay - The time is now {}", sdf.format(new Date()));
    }

    @Scheduled(fixedRate = 3000)
    void reportCurrentTimeFixedRate() {
        LOG.info("reportCurrentTimeFixedRate - The time is now {}", sdf.format(new Date()));
    }

    @Scheduled(fixedRate = 3000)
    void reportCurrentTimeFixedRateAndSleep() throws InterruptedException {
        LOG.info("reportCurrentTimeFixedRateAndSleep - The time is now {}", sdf.format(new Date()));
        Thread.sleep(4000);
    }

    @Scheduled(fixedRate = 3000)
    @Async
    void reportCurrentTimeFixedRateAndSleepWithAsync() throws InterruptedException {
        LOG.info("reportCurrentTimeFixedRateAndSleepWithAsync - The time is now {}", sdf.format(new Date()));
        Thread.sleep(4000);
    }
}
