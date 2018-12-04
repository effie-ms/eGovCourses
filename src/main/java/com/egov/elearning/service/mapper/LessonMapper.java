package com.egov.elearning.service.mapper;

import com.egov.elearning.domain.*;
import com.egov.elearning.service.dto.LessonDTO;

import org.mapstruct.*;

// Mapper for the entity Lesson and its DTO LessonDTO.
@Mapper(componentModel = "spring", uses = {CourseMapper.class})
public interface LessonMapper extends EntityMapper<LessonDTO, Lesson> {

    @Mapping(source = "course.id", target = "courseId")
    LessonDTO toDto(Lesson lesson);

    @Mapping(source = "courseId", target = "course")
    @Mapping(target = "resources", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    Lesson toEntity(LessonDTO lessonDTO);

    default Lesson fromId(Long id) {
        if (id == null) {
            return null;
        }
        Lesson lesson = new Lesson();
        lesson.setId(id);
        return lesson;
    }
}
