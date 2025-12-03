package com.healthnutrition.bodymetric

import com.healthnutrition.bodymetric.exception.BodyMetricNotFoundException
import org.springframework.stereotype.Repository

@Repository
class BodyMetricRepositoryImpl(
	private val bodyMetricJpaRepository: BodyMetricJpaRepository
) : BodyMetricRepository {
	override fun save(bodyMetric: BodyMetric): BodyMetric {
		val savedEntity = bodyMetricJpaRepository.save(BodyMetricMapper.toEntity(bodyMetric))
		return BodyMetricMapper.toDomain(savedEntity)
	}

	override fun getByAccountIdOrThrow(accountId: Long): BodyMetric {
		return bodyMetricJpaRepository.findByAccountId(accountId)?.let { BodyMetricMapper.toDomain(it) }
			?: throw BodyMetricNotFoundException("Account id: $accountId 에 해당하는 신체 정보는 존재하지 않습니다.")
	}
}
