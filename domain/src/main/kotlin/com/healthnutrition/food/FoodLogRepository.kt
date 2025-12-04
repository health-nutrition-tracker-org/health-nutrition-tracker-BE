package com.healthnutrition.food

import java.time.LocalDateTime

interface FoodLogRepository {
	fun save(foodLog: FoodLog): FoodLog

	fun saveAll(foodLogs: List<FoodLog>): List<FoodLog>

	fun getAllByAccountIdEqualsAndCreatedAtBetween(accountId: Long, startDate: LocalDateTime, endDate: LocalDateTime): List<FoodLog>
}
