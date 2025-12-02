package com.healthnutrition.food.infrastructure.persistence

import com.healthnutrition.food.domain.FoodLogRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class FoodLogRepositoryImpl(
	private val foodLogJpaRepository: FoodLogJpaRepository
) : FoodLogRepository {
	override fun save(entity: FoodLogEntity): FoodLogEntity =
		foodLogJpaRepository.save(entity)

	override fun saveAll(entities: List<FoodLogEntity>): List<FoodLogEntity> =
		foodLogJpaRepository.saveAll(entities)

	override fun getAllByAccountIdEqualsAndCreatedAtBetween(
		accountId: Long,
		startDate: LocalDateTime,
		endDate: LocalDateTime
	): List<FoodLogEntity> =
		foodLogJpaRepository.findAllByAccountIdEqualsAndCreatedAtBetween(accountId, startDate, endDate)
}
