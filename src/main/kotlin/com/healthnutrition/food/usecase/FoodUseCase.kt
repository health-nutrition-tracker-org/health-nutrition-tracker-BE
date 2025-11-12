package com.healthnutrition.food.usecase

import com.healthnutrition.food.domain.DataGoClient
import com.healthnutrition.food.domain.FoodLogRepository
import com.healthnutrition.food.domain.exception.FoodSearchFailException
import com.healthnutrition.food.infrastructure.external.dto.DataGoClientRequest
import com.healthnutrition.food.usecase.dto.FoodDto
import org.springframework.stereotype.Service

@Service
class FoodUseCase(
	private val dataGoClient: DataGoClient,
	private val foodLogRepository: FoodLogRepository
) {
	fun searchFoods(keyword: String, page: Int, numOfRows: Int): List<FoodDto.Search> {
		val foodNutrition = dataGoClient.getFoodNutritionInfo(
			foodNutritionRequest = DataGoClientRequest.FoodNutrition(
				page = page,
				numOfRows = numOfRows,
				foodName = keyword
			)
		).block() ?: run { throw FoodSearchFailException() }

		return foodNutrition.body.items.map { foodItem ->
			FoodDto.Search(
				foodName = foodItem.foodName,
				foodCategoryName = foodItem.foodCategoryName,
				servingSize = foodItem.fetchServingSize(),
				kcal = foodItem.kcal.toBigDecimal(),
				carbohydrate = foodItem.carbohydrate.toBigDecimal(),
				sugar = foodItem.sugar.toBigDecimal(),
				protein = foodItem.protein.toBigDecimal(),
				fat = foodItem.fat.toBigDecimal(),
				saturatedFattyAcid = foodItem.saturatedFattyAcid.toBigDecimal(),
				transFattyAcid = foodItem.transFattyAcid.toBigDecimal(),
				cholesterol = foodItem.cholesterol.toBigDecimal(),
				sodium = foodItem.sodium.toBigDecimal(),
				dietaryFiber = foodItem.dietaryFiber.toBigDecimal()
			)
		}
	}
}
