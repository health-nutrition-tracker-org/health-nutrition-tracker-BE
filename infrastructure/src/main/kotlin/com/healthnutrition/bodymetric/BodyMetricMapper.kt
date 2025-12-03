package com.healthnutrition.bodymetric

import com.healthnutrition.bodymetric.dto.BodyMetricDto

object BodyMetricMapper {
	fun toEntity(dto: BodyMetricDto.Post): BodyMetricEntity =
		BodyMetricEntity(
			accountId = dto.accountId,
			height = dto.height,
			weight = dto.weight,
			bodyFatRate = dto.bodyFatRate,
			activityLevel = dto.activityLevel
		)

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