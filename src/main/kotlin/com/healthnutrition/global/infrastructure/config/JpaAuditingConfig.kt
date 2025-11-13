package com.healthnutrition.global.infrastructure.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
@EnableJpaAuditing
// test 프로파일에선 비활성화
@Profile("!test")
class JpaAuditingConfig
