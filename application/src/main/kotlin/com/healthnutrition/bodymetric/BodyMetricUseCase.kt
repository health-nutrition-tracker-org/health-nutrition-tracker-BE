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
			entity = BodyMetricMapper.toEntity(request)
		)
	}
}