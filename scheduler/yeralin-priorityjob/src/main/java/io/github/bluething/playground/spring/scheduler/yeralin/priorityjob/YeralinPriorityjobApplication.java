package io.github.bluething.playground.spring.scheduler.yeralin.priorityjob;

import io.github.bluething.playground.spring.scheduler.yeralin.priorityjob.job.FutureCustomTask;
import io.github.bluething.playground.spring.scheduler.yeralin.priorityjob.job.Job;
import io.github.bluething.playground.spring.scheduler.yeralin.priorityjob.job.JobBusiness;
import io.github.bluething.playground.spring.scheduler.yeralin.priorityjob.job.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;

@SpringBootApplication
public class YeralinPriorityjobApplication {

	@Autowired
	private TaskExecutor taskExecutor;
	@Autowired
	private JobBusiness jobBusiness;

	public static void main(String[] args) {
		SpringApplication.run(YeralinPriorityjobApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			Task initialFirst = new Task(jobBusiness::performSomethingOn, new Job(1L, 1L));
			Task initialSecond = new Task(jobBusiness::performSomethingOn, new Job(2L, 2L));
			// Core pool size is reached, start appending jobs to the queue
			Task waitingInQueueFirst = new Task(jobBusiness::performSomethingOn, new Job(3L, 5L));
			Task waitingInQueueSecond = new Task(jobBusiness::performSomethingOn, new Job(4L, 1L));
			Task waitingInQueueThird = new Task(jobBusiness::performSomethingOn, new Job(5L, 10L));
			taskExecutor.execute(new FutureCustomTask(initialFirst));
			taskExecutor.execute(new FutureCustomTask(initialSecond));
            /*
             Once initial jobs are finished, `waitingInQueueSecond` Job will get the first freed thread
             since it's priority is higher than priorities of `waitingInQueueFirst` and `waitingInQueueThird`
            */
			taskExecutor.execute(new FutureCustomTask(waitingInQueueFirst));
			taskExecutor.execute(new FutureCustomTask(waitingInQueueSecond));
			taskExecutor.execute(new FutureCustomTask(waitingInQueueThird));
		};
	}

}
