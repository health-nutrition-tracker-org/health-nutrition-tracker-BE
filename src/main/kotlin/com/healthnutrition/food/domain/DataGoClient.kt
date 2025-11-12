package com.healthnutrition.food.domain

import com.healthnutrition.food.infrastructure.external.dto.DataGoClientRequest
import com.healthnutrition.food.infrastructure.external.dto.DataGoClientResponse
import reactor.core.publisher.Mono

interface DataGoClient {
	fun getFoodNutritionInfo(
		foodNutritionRequest: DataGoClientRequest.FoodNutrition
	): Mono<DataGoClientResponse.FoodNutrition>
}
