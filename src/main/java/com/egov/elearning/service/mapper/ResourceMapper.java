package com.egov.elearning.service.mapper;

import com.egov.elearning.domain.*;
import com.egov.elearning.service.dto.ResourceDTO;

import org.mapstruct.*;

// Mapper for the entity Resource and its DTO ResourceDTO.
@Mapper(componentModel = "spring", uses = {LessonMapper.class})
public interface ResourceMapper extends EntityMapper<ResourceDTO, Resource> {

    @Mapping(source = "lesson.id", target = "lessonId")
    ResourceDTO toDto(Resource resource);

    @Mapping(source = "lessonId", target = "lesson")
    Resource toEntity(ResourceDTO resourceDTO);

    default Resource fromId(Long id) {
        if (id == null) {
            return null;
        }
        Resource resource = new Resource();
        resource.setId(id);
        return resource;
    }
}
