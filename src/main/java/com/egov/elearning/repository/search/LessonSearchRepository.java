package com.egov.elearning.repository.search;

import com.egov.elearning.domain.Lesson;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

// Spring Data Elasticsearch repository for the Lesson entity.
public interface LessonSearchRepository extends ElasticsearchRepository<Lesson, Long> {
}
