package com.egov.elearning.service.impl;

import com.egov.elearning.service.LessonService;
import com.egov.elearning.domain.Lesson;
import com.egov.elearning.repository.LessonRepository;
import com.egov.elearning.repository.search.LessonSearchRepository;
import com.egov.elearning.service.dto.LessonDTO;
import com.egov.elearning.service.mapper.LessonMapper;
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
public class LessonServiceImpl implements LessonService {

    private final Logger log = LoggerFactory.getLogger(LessonServiceImpl.class);

    private LessonRepository lessonRepository;

    private LessonMapper lessonMapper;

    private LessonSearchRepository lessonSearchRepository;

    public LessonServiceImpl(LessonRepository lessonRepository, LessonMapper lessonMapper, LessonSearchRepository lessonSearchRepository) {
        this.lessonRepository = lessonRepository;
        this.lessonMapper = lessonMapper;
        this.lessonSearchRepository = lessonSearchRepository;
    }

    /**
     * Save a lesson.
     *
     * @param lessonDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LessonDTO save(LessonDTO lessonDTO) {
        log.debug("Request to save Lesson : {}", lessonDTO);

        Lesson lesson = lessonMapper.toEntity(lessonDTO);
        lesson = lessonRepository.save(lesson);
        LessonDTO result = lessonMapper.toDto(lesson);
        lessonSearchRepository.save(lesson);
        return result;
    }

    /**
     * Get all the lessons.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LessonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Lessons");
        return lessonRepository.findAll(pageable)
            .map(lessonMapper::toDto);
    }


    /**
     * Get one lesson by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LessonDTO> findOne(Long id) {
        log.debug("Request to get Lesson : {}", id);
        return lessonRepository.findById(id)
            .map(lessonMapper::toDto);
    }

    /**
     * Delete the lesson by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Lesson : {}", id);
        lessonRepository.deleteById(id);
        lessonSearchRepository.deleteById(id);
    }

    /**
     * Search for the lesson corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LessonDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Lessons for query {}", query);
        return lessonSearchRepository.search(queryStringQuery(query), pageable)
            .map(lessonMapper::toDto);
    }
}
