package com.ceadar.uidashboard.controller;

import com.ceadar.uidashboard.datamodel.dto.TaskDTO;
import com.ceadar.uidashboard.datamodel.entity.TaskEntity;
import com.ceadar.uidashboard.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@CrossOrigin
@RestController
@RequestMapping("/task")
public class TaskController extends BaseController<TaskEntity, TaskDTO, Long, TaskService> {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;

    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskDTO> entities = taskService.findAllDTOs(taskService.findAll());
        logger.info("get AllTasks method is called in controller, {} tasks found", entities.size());
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO taskDTO) {
        logger.info("add Task to Database");
        TaskEntity task = taskService.addTask(taskService.toEntity(taskDTO));
        return new ResponseEntity<>(taskService.toDto(task), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO taskDTO) {
        logger.info("update Task to Database");
        TaskEntity task = taskService.onUpdate(taskService.toEntity(taskDTO));
        return new ResponseEntity<>(taskService.toDto(task), HttpStatus.OK);
    }
}
