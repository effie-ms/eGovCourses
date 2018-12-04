package com.egov.elearning.service.mapper;

import com.egov.elearning.domain.*;
import com.egov.elearning.service.dto.CourseDTO;

import org.mapstruct.*;

// Mapper for the entity Course and its DTO CourseDTO.
@Mapper(componentModel = "spring", uses = {})
public interface CourseMapper extends EntityMapper<CourseDTO, Course> {

    @Mapping(target = "lessons", ignore = true)
    Course toEntity(CourseDTO courseDTO);

    default Course fromId(Long id) {
        if (id == null) {
            return null;
        }
        Course course = new Course();
        course.setId(id);
        return course;
    }
}
