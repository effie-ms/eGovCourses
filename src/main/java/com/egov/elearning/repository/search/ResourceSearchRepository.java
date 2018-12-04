package com.egov.elearning.repository.search;

import com.egov.elearning.domain.Resource;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

// Spring Data Elasticsearch repository for the Resource entity.
public interface ResourceSearchRepository extends ElasticsearchRepository<Resource, Long> {
}
