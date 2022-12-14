package com.egov.elearning.repository.search;

import com.egov.elearning.domain.Task;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

// Spring Data Elasticsearch repository for the Task entity.
public interface TaskSearchRepository extends ElasticsearchRepository<Task, Long> {
}
