package com.healthnutrition.food.infrastructure.persistence

import com.healthnutrition.food.domain.FoodLogRepository
import org.springframework.stereotype.Repository

@Repository
class FoodLogRepositoryImpl(
	private val foodLogJpaRepository: FoodLogJpaRepository
) : FoodLogRepository {
	override fun save(entity: FoodLogEntity): FoodLogEntity =
		foodLogJpaRepository.save(entity)
}
