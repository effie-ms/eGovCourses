package com.egov.elearning.service.impl;

import com.egov.elearning.service.ResourceService;
import com.egov.elearning.domain.Resource;
import com.egov.elearning.repository.ResourceRepository;
import com.egov.elearning.repository.search.ResourceSearchRepository;
import com.egov.elearning.service.dto.ResourceDTO;
import com.egov.elearning.service.mapper.ResourceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

    private final Logger log = LoggerFactory.getLogger(ResourceServiceImpl.class);

    private ResourceRepository resourceRepository;

    private ResourceMapper resourceMapper;

    private ResourceSearchRepository resourceSearchRepository;

    public ResourceServiceImpl(ResourceRepository resourceRepository, ResourceMapper resourceMapper, ResourceSearchRepository resourceSearchRepository) {
        this.resourceRepository = resourceRepository;
        this.resourceMapper = resourceMapper;
        this.resourceSearchRepository = resourceSearchRepository;
    }

    /**
     * Save a resource.
     *
     * @param resourceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ResourceDTO save(ResourceDTO resourceDTO) {
        log.debug("Request to save Resource : {}", resourceDTO);

        Resource resource = resourceMapper.toEntity(resourceDTO);
        resource = resourceRepository.save(resource);
        ResourceDTO result = resourceMapper.toDto(resource);
        resourceSearchRepository.save(resource);
        return result;
    }

    /**
     * Get all the resources.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ResourceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Resources");
        return resourceRepository.findAll(pageable)
            .map(resourceMapper::toDto);
    }


    /**
     * Get one resource by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ResourceDTO> findOne(Long id) {
        log.debug("Request to get Resource : {}", id);
        return resourceRepository.findById(id)
            .map(resourceMapper::toDto);
    }

    /**
     * Delete the resource by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Resource : {}", id);
        resourceRepository.deleteById(id);
        resourceSearchRepository.deleteById(id);
    }

    /**
     * Search for the resource corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ResourceDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Resources for query {}", query);
        return resourceSearchRepository.search(queryStringQuery(query), pageable)
            .map(resourceMapper::toDto);
    }
}
