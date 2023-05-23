package io.github.bluething.playground.spring.scheduler.yeralin.priorityjob.job;

import java.util.concurrent.FutureTask;

public class FutureCustomTask extends FutureTask<FutureCustomTask> implements Comparable<FutureCustomTask> {

    private Task task;

    public FutureCustomTask(Task task) {
        super(task, null);
        this.task = task;
    }

    @Override
    public int compareTo(FutureCustomTask futureCustomTask) {
        return task.getJob().priority().compareTo(futureCustomTask.task.getJob().priority());
    }
}
