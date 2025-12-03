package com.healthnutrition.food

import com.healthnutrition.food.dto.FoodDto
import com.healthnutrition.food.exception.FoodSearchFailException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FoodUseCase(
	private val dataGoClient: DataGoClient,
	private val foodLogRepository: FoodLogRepository
) {
	fun searchFoods(keyword: String, page: Int, numOfRows: Int): List<FoodDto.Search> {
		val foodNutrition = dataGoClient.getFoodNutritionData(
			page = page,
			numOfRows = numOfRows,
			foodName = keyword
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

	@Transactional
	fun postFoodLogs(accountId: Long, requests: List<FoodDto.CreateLog>) {
		val logEntities = requests.map {
			FoodMapper.toLogEntity(accountId, it)
		}
		foodLogRepository.saveAll(logEntities)
	}
}