package com.egov.elearning.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.egov.elearning.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.egov.elearning.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.egov.elearning.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.egov.elearning.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.egov.elearning.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.egov.elearning.domain.Course.class.getName(), jcacheConfiguration);
            cm.createCache(com.egov.elearning.domain.Course.class.getName() + ".lessons", jcacheConfiguration);
            cm.createCache(com.egov.elearning.domain.Lesson.class.getName(), jcacheConfiguration);
            cm.createCache(com.egov.elearning.domain.Lesson.class.getName() + ".resources", jcacheConfiguration);
            cm.createCache(com.egov.elearning.domain.Lesson.class.getName() + ".tasks", jcacheConfiguration);
            cm.createCache(com.egov.elearning.domain.Resource.class.getName(), jcacheConfiguration);
            cm.createCache(com.egov.elearning.domain.Task.class.getName(), jcacheConfiguration);
        };
    }
}
