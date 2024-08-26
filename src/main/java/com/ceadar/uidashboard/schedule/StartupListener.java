package com.ceadar.uidashboard.schedule;

import com.ceadar.uidashboard.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Consumer;

@Component
public class StartupListener implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(StartupListener.class);

    private final TaskService taskService;
    private final SchedulerUtil schedulerUtil;

    private final Consumer<String[]> runTasks = args -> {
        logger.info("Running tasks from CommandLineRunner...");
        createTasks();
    };

    @Autowired
    public StartupListener(TaskService taskService, SchedulerUtil schedulerUtil) {
        this.taskService = taskService;
        this.schedulerUtil = schedulerUtil;
    }

    @Override
    public void run(String... args) throws Exception {
        runTasks.accept(args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        logger.info("Application is ready. initial new Tasks...");
        taskService.findAll().stream().forEach(task -> {
            logger.info("new task: {}", task.getName());});
    }

    private void createTasks() {
        logger.info("creating new tasks and put them in database at {}", LocalDateTime.now());
        schedulerUtil.addNewBatchTasks();
    }
}
