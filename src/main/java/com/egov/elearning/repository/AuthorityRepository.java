package com.egov.elearning.repository;

import com.egov.elearning.domain.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

// Spring Data JPA repository for the Authority entity.
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
