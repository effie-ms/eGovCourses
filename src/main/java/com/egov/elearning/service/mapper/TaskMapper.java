package com.egov.elearning.service.mapper;

import com.egov.elearning.domain.*;
import com.egov.elearning.service.dto.TaskDTO;

import org.mapstruct.*;

// Mapper for the entity Task and its DTO TaskDTO.
@Mapper(componentModel = "spring", uses = {LessonMapper.class})
public interface TaskMapper extends EntityMapper<TaskDTO, Task> {

    @Mapping(source = "lesson.id", target = "lessonId")
    TaskDTO toDto(Task task);

    @Mapping(source = "lessonId", target = "lesson")
    Task toEntity(TaskDTO taskDTO);

    default Task fromId(Long id) {
        if (id == null) {
            return null;
        }
        Task task = new Task();
        task.setId(id);
        return task;
    }
}
