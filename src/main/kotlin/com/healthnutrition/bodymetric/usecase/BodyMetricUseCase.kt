package com.healthnutrition.bodymetric.usecase

import com.healthnutrition.bodymetric.domain.BodyMetricRepository
import com.healthnutrition.bodymetric.infrastructure.persistence.BodyMetricMapper
import com.healthnutrition.bodymetric.usecase.dto.BodyMetricDto
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
