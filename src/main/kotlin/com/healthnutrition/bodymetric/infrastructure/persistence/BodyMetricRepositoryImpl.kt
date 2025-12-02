package com.healthnutrition.bodymetric.infrastructure.persistence

import com.healthnutrition.bodymetric.domain.BodyMetricRepository
import com.healthnutrition.bodymetric.domain.exception.BodyMetricNotFoundException
import org.springframework.stereotype.Repository

@Repository
class BodyMetricRepositoryImpl(
	private val bodyMetricJpaRepository: BodyMetricJpaRepository
) : BodyMetricRepository {
	override fun save(entity: BodyMetricEntity): BodyMetricEntity =
		bodyMetricJpaRepository.save(entity)

	override fun getByAccountIdOrThrow(accountId: Long): BodyMetricEntity {
		return bodyMetricJpaRepository.findByAccountId(accountId)
			?: throw BodyMetricNotFoundException("Account id: $accountId 에 해당하는 신체 정보는 존재하지 않습니다.")
	}
}
