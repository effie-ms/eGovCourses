package com.egov.elearning.config;

import org.springframework.context.annotation.Configuration;

import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableJpaRepositories("com.egov.elearning.repository")
@EnableTransactionManagement
@EnableElasticsearchRepositories("com.egov.elearning.repository.search")
public class DatabaseConfiguration {


}
