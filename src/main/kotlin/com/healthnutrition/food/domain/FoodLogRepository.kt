package com.healthnutrition.food.domain

import com.healthnutrition.food.infrastructure.persistence.FoodLogEntity

interface FoodLogRepository {
	fun save(entity: FoodLogEntity): FoodLogEntity
}
