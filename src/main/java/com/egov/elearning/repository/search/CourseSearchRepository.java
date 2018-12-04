package com.egov.elearning.repository.search;

import com.egov.elearning.domain.Course;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

// Spring Data Elasticsearch repository for the Course entity.
public interface CourseSearchRepository extends ElasticsearchRepository<Course, Long> {
}
