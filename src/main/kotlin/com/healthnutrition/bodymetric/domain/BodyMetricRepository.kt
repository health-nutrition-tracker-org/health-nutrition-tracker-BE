package com.healthnutrition.bodymetric.domain

import com.healthnutrition.bodymetric.infrastructure.persistence.BodyMetricEntity

interface BodyMetricRepository {
	fun save(entity: BodyMetricEntity): BodyMetricEntity

	fun getByAccountIdOrThrow(accountId: Long): BodyMetricEntity
}
