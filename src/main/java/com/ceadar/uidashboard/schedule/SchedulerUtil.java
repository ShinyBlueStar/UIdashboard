package com.ceadar.uidashboard.schedule;

import com.ceadar.uidashboard.datamodel.entity.Status;
import com.ceadar.uidashboard.datamodel.entity.TaskEntity;
import com.ceadar.uidashboard.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Service
public class SchedulerUtil {
    private static final Logger logger = LoggerFactory.getLogger(SchedulerUtil.class);

    private final TaskService taskService;

    @Autowired
    public SchedulerUtil(TaskService taskService) {
        this.taskService = taskService;
    }

    public static final Predicate<Status> isFinishedStatus = status -> Status.FINISHED.equals(status);

    public static final Predicate<Status> statusIsDone = status ->
        Status.TERMINATED.equals(status) || isFinishedStatus.test(status);

    public static final Supplier<Status> randomStatusSupplier = () -> {
        int statusSize = Status.values().length;
        int statusCode = ThreadLocalRandom.current().nextInt(0, statusSize);
        logger.info("Generated number for assigning a status to a task: " + statusCode);
        return Status.values()[statusCode];
    };

    public static final Function<Integer, LocalDateTime> generateRandomDateAndTime = daysInPast -> {
        Random random = new Random();
        return LocalDateTime.now()
                .minusDays(random.nextInt(daysInPast + 1))
                .minusHours(random.nextInt(24))
                .minusMinutes(random.nextInt(60))
                .minusSeconds(random.nextInt(60));
    };

    private static final Function<Long, TaskEntity> createTask = taskCount -> {
        Status status = randomStatusSupplier.get();
        TaskEntity task = new TaskEntity();
        task.setName("Task " + taskCount);
        task.setCreatedAt(generateRandomDateAndTime.apply(5));
        logger.info("Random LocalDateTime created for field CreateAt");

        if (statusIsDone.test(status)) {
            status = Status.ONHOLD;
        }
        task.setStatus(status);

        return task;
    };

    public void addNewBatchTasks() {
        logger.info("generate new task");
        List<TaskEntity> tasks = new ArrayList<>();
        Long taskCounts = taskService.count();

        for (int i = 0; i < 4; i++) {
            taskCounts++;
            TaskEntity newTask = createTask.apply(taskCounts);
            tasks.add(newTask);
        }
        taskService.saveAll(tasks);
    }

}
