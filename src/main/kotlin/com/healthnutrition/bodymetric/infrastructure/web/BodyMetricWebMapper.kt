package com.healthnutrition.bodymetric.infrastructure.web

import com.healthnutrition.bodymetric.infrastructure.web.dto.BodyMetricRequest
import com.healthnutrition.bodymetric.usecase.dto.BodyMetricDto

object BodyMetricWebMapper {
	fun toPostDto(accountId: Long, request: BodyMetricRequest.Post): BodyMetricDto.Post =
		BodyMetricDto.Post(
			accountId = accountId,
			height = request.height,
			weight = request.weight,
			bodyFatRate = request.bodyFatRate,
			activityLevel = request.activityLevel
		)
}
