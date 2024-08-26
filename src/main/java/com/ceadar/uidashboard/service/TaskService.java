package com.ceadar.uidashboard.service;

import com.ceadar.uidashboard.exception.TaskNotFoundException;
import com.ceadar.uidashboard.datamodel.dto.TaskDTO;
import com.ceadar.uidashboard.datamodel.entity.TaskEntity;
import com.ceadar.uidashboard.mapper.TaskMapper;
import com.ceadar.uidashboard.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService extends AbstractGeneralService<TaskDTO, TaskEntity, Long> {
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    public TaskService(TaskRepository repository, TaskMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public TaskEntity onUpdate(TaskEntity taskEntity) {
        logger.info("updating, task id: {}", taskEntity.getId());
        return repository.save(taskEntity);
    }

    @Override
    public Long onSave(TaskEntity taskEntity) {
        logger.info("saving, task name: {}", taskEntity.getName());
        return repository.save(taskEntity).getId();
    }

    @Override
    public List<TaskEntity> findAll() {
        return repository.findAll();
    }

    public List<TaskDTO> findAllDTOs(List<TaskEntity> ls) {
        return ls.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public TaskEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task by Id " + id + " was not found"));
    }

    @Override
    public TaskDTO toDto(TaskEntity taskEntity) {
        return (TaskDTO) mapper.entityToDto(taskEntity);
    }

    @Override
    public TaskEntity toEntity(TaskDTO taskDto){
        TaskEntity taskEntity = new TaskEntity();
        return mapper.dtoToEntity(taskDto);
    }

    public List<TaskEntity> findAllByDoneIsFalse() {

        return repository.findAllByIsDoneIsFalse();
    }
    @Override
    public Long count() {

        return repository.count();
    }

    public TaskEntity addTask(TaskEntity entity) {
        return repository.save(entity);
    }

    @Override
    public List<TaskEntity> saveAll(List<TaskEntity> tasks) {
        return repository.saveAllAndFlush(tasks);
    }
}
