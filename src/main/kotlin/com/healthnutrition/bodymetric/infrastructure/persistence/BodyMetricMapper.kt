package com.healthnutrition.bodymetric.infrastructure.persistence

import com.healthnutrition.bodymetric.domain.BodyMetric
import com.healthnutrition.bodymetric.usecase.dto.BodyMetricDto

object BodyMetricMapper {
	fun toEntity(dto: BodyMetricDto.Post): BodyMetricEntity =
		BodyMetricEntity(
			accountId = dto.accountId,
			height = dto.height,
			weight = dto.weight,
			bodyFatRate = dto.bodyFatRate,
			activityLevel = dto.activityLevel
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