package com.healthnutrition.food.domain

import com.healthnutrition.food.infrastructure.persistence.FoodLogEntity
import java.time.LocalDateTime

interface FoodLogRepository {
	fun save(entity: FoodLogEntity): FoodLogEntity

	fun saveAll(entities: List<FoodLogEntity>): List<FoodLogEntity>

	fun getAllByAccountIdEqualsAndCreatedAtBetween(accountId: Long, startDate: LocalDateTime, endDate: LocalDateTime): List<FoodLogEntity>
}
