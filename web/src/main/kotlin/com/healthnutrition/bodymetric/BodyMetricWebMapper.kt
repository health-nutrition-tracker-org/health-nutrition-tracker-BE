package com.healthnutrition.bodymetric

import com.healthnutrition.bodymetric.dto.BodyMetricDto
import com.healthnutrition.bodymetric.dto.BodyMetricRequest

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
