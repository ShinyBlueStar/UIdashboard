package com.ceadar.uidashboard.mapper;

import com.ceadar.uidashboard.datamodel.dto.TaskDTO;
import com.ceadar.uidashboard.datamodel.entity.TaskEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TaskMapper extends BaseMapper<TaskEntity, TaskDTO, Long>{

    @Override
    TaskEntity dtoToEntity(TaskDTO dto);

    @Override
    TaskDTO entityToDto(TaskEntity entity);

    @Override
    default Class<TaskDTO> getDtoType() {
        return TaskDTO.class;
    }

    @Override
    default Class<TaskEntity> getEntityType() {
        return TaskEntity.class;
    }

}