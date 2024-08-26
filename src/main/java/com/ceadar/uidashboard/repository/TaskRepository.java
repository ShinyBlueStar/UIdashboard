package com.ceadar.uidashboard.repository;

import com.ceadar.uidashboard.datamodel.dto.TaskDTO;
import com.ceadar.uidashboard.datamodel.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends BaseRepository<TaskEntity, Long> , JpaRepository<TaskEntity, Long> {


    List<TaskEntity> findAllByIsDoneIsFalse();

    List<TaskEntity> findByName(String name);

}
