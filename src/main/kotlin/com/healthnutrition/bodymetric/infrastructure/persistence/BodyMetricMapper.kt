package com.healthnutrition.bodymetric.infrastructure.persistence

import com.healthnutrition.bodymetric.domain.BodyMetric

object BodyMetricMapper {
	fun toDomain(entity: BodyMetricEntity): BodyMetric {
		return BodyMetric(
			accountId = entity.accountId,
			height = entity.height,
			weight = entity.weight,
			bodyFatRate = entity.bodyFatRate,
			activityLevel = entity.activityLevel
		)
	}
}