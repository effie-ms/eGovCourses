package com.egov.elearning.service.impl;

import com.egov.elearning.service.CourseService;
import com.egov.elearning.domain.Course;
import com.egov.elearning.repository.CourseRepository;
import com.egov.elearning.repository.search.CourseSearchRepository;
import com.egov.elearning.service.dto.CourseDTO;
import com.egov.elearning.service.mapper.CourseMapper;
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
public class CourseServiceImpl implements CourseService {

    private final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);

    private CourseRepository courseRepository;

    private CourseMapper courseMapper;

    private CourseSearchRepository courseSearchRepository;

    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper, CourseSearchRepository courseSearchRepository) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.courseSearchRepository = courseSearchRepository;
    }

    /**
     * Save a course.
     *
     * @param courseDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CourseDTO save(CourseDTO courseDTO) {
        log.debug("Request to save Course : {}", courseDTO);

        Course course = courseMapper.toEntity(courseDTO);
        course = courseRepository.save(course);
        CourseDTO result = courseMapper.toDto(course);
        courseSearchRepository.save(course);
        return result;
    }

    /**
     * Get all the courses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CourseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Courses");
        return courseRepository.findAll(pageable)
            .map(courseMapper::toDto);
    }


    /**
     * Get one course by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CourseDTO> findOne(Long id) {
        log.debug("Request to get Course : {}", id);
        return courseRepository.findById(id)
            .map(courseMapper::toDto);
    }

    /**
     * Delete the course by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Course : {}", id);
        courseRepository.deleteById(id);
        courseSearchRepository.deleteById(id);
    }

    /**
     * Search for the course corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CourseDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Courses for query {}", query);
        return courseSearchRepository.search(queryStringQuery(query), pageable)
            .map(courseMapper::toDto);
    }
}
