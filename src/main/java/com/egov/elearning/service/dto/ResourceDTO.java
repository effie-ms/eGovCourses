package com.egov.elearning.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.egov.elearning.domain.enumeration.ResourceType;

public class ResourceDTO implements Serializable {

    private Long id;

    private String resourceName;

    private String resourceDescription;

    private String resourceURL;

    private ResourceType resourceType;

    private Long lessonId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceDescription() {
        return resourceDescription;
    }

    public void setResourceDescription(String resourceDescription) {
        this.resourceDescription = resourceDescription;
    }

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ResourceDTO resourceDTO = (ResourceDTO) o;
        if (resourceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), resourceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ResourceDTO{" +
            "id=" + getId() +
            ", resourceName='" + getResourceName() + "'" +
            ", resourceDescription='" + getResourceDescription() + "'" +
            ", resourceURL='" + getResourceURL() + "'" +
            ", resourceType='" + getResourceType() + "'" +
            ", lesson=" + getLessonId() +
            "}";
    }
}
