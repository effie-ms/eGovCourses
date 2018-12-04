package com.egov.elearning.repository;

import com.egov.elearning.domain.Resource;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


// Spring Data repository for the Resource entity.
@SuppressWarnings("unused")
@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

}
