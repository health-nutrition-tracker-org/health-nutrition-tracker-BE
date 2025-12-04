package com.healthnutrition.bodymetric

object BodyMetricMapper {
	fun toEntity(domain: BodyMetric): BodyMetricEntity =
		BodyMetricEntity(
			accountId = domain.accountId,
			height = domain.height,
			weight = domain.weight,
			bodyFatRate = domain.bodyFatRate,
			activityLevel = domain.activityLevel
		)

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