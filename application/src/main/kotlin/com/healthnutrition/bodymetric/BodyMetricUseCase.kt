package com.healthnutrition.bodymetric

import com.healthnutrition.bodymetric.dto.BodyMetricDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BodyMetricUseCase(
	private val bodyMetricRepository: BodyMetricRepository
) {
	@Transactional
	fun postBodyMetric(request: BodyMetricDto.Post) {
		bodyMetricRepository.save(
			BodyMetric(
				accountId = request.accountId,
				height = request.height,
				weight = request.weight,
				bodyFatRate = request.bodyFatRate,
				activityLevel = request.activityLevel
			)
		)
	}
}