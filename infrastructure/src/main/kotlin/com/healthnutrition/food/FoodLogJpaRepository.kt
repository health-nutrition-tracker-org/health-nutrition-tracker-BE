package com.healthnutrition.food

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface FoodLogJpaRepository : JpaRepository<FoodLogEntity, Long> {
	fun findAllByAccountIdEqualsAndCreatedAtBetween(accountId: Long, startDate: LocalDateTime, endDate: LocalDateTime): List<FoodLogEntity>
}
