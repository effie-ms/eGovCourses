package com.egov.elearning.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

 // Configured in the application.yml file
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

}
