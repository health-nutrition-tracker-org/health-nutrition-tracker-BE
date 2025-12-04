package com.healthnutrition.bodymetric

import org.springframework.data.jpa.repository.JpaRepository

interface BodyMetricJpaRepository : JpaRepository<BodyMetricEntity, Long> {
	fun findByAccountId(accountId: Long): BodyMetricEntity?
}
