package com.ceadar.uidashboard.schedule;

import com.ceadar.uidashboard.datamodel.entity.Status;
import com.ceadar.uidashboard.datamodel.entity.TaskEntity;
import com.ceadar.uidashboard.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

@Component
public class Scheduler {
    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    private final TaskService taskService;
    private final SchedulerUtil schedulerUtil;

    @Autowired
    public Scheduler(TaskService taskService, SchedulerUtil schedulerUtil) {
        this.taskService = taskService;
        this.schedulerUtil = schedulerUtil;
    }

    @Scheduled(fixedRate = 60000)
    public void updateTaskStatuses() {
        List<TaskEntity> tasks = taskService.findAllByDoneIsFalse();
        logger.info("tasks fetching from database for update, count: {}", tasks.size());
        for (TaskEntity taskEntity : tasks) {
            if (taskEntity.isDone()) {
                logger.info("task {} , {} is already done", taskEntity.getId(), taskEntity.getName());
                continue;
            }
            Status status = SchedulerUtil.randomStatusSupplier.get();
            if (SchedulerUtil.statusIsDone.test(status)) {// we do not check new Status with old status to save more time
                taskEntity.setEndedAt(LocalDateTime.now());
                taskEntity.setExecutionTime(Duration.between(taskEntity.getCreatedAt(), taskEntity.getEndedAt()).toSeconds());
                taskEntity.setDone(true);
            }
            taskEntity.setStatus(status);
            taskService.onUpdate(taskEntity);
            logger.debug("task {} , {} is updated", taskEntity.getId(), taskEntity.getName());
        }
    }

    @Scheduled(fixedRate = 59000)
    public void addNewTask() {
        logger.info("add new batch task to database at {}", LocalDateTime.now());
        List<TaskEntity> tasks = taskService.findAll();
        if (!tasks.isEmpty()) {
            schedulerUtil.addNewBatchTasks();
        }
        logger.info("add new batch task finished at {}", LocalDateTime.now());

    }
}