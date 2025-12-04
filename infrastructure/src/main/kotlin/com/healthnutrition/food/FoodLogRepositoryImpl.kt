package com.healthnutrition.food

import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class FoodLogRepositoryImpl(
	private val foodLogJpaRepository: FoodLogJpaRepository
) : FoodLogRepository {
	override fun save(foodLog: FoodLog): FoodLog {
		val savedEntity = foodLogJpaRepository.save(FoodMapper.toLogEntity(foodLog))
		return FoodMapper.toLogDomain(savedEntity)
	}

	override fun saveAll(foodLogs: List<FoodLog>): List<FoodLog> {
		val savedEntities = foodLogJpaRepository.saveAll(foodLogs.map { FoodMapper.toLogEntity(it) })
		return savedEntities.map { FoodMapper.toLogDomain(it) }
	}

	override fun getAllByAccountIdEqualsAndCreatedAtBetween(
		accountId: Long,
		startDate: LocalDateTime,
		endDate: LocalDateTime
	): List<FoodLog> =
		foodLogJpaRepository.findAllByAccountIdEqualsAndCreatedAtBetween(accountId, startDate, endDate).map { FoodMapper.toLogDomain(it) }
}
