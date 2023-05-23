package io.github.bluething.playground.spring.scheduler.yeralin.priorityjob.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JobBusiness {
    private static final Logger LOG = LoggerFactory.getLogger(JobBusiness.class);

    public void performSomethingOn(Job job) {
        LOG.info("Received Job with Id:{} of priority:{}", job.id(), job.priority());
        LOG.info("Sleeping Id:{} for 10 seconds", job.id());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOG.info("Finished Job with ID:{} of priority:{}", job.id(), job.priority());
    }
}
