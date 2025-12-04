package com.healthnutrition.food

import com.healthnutrition.food.dto.FoodDto
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
		)

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
		val foodLogs = requests.map {
			FoodLog(
				accountId = accountId,
				foodName = it.foodName,
				servingSize = it.servingSize,
				kcal = it.kcal,
				carbohydrate = it.carbohydrate,
				sugar = it.sugar,
				protein = it.protein,
				fat = it.fat,
				saturatedFattyAcid = it.saturatedFattyAcid,
				transFattyAcid = it.transFattyAcid,
				cholesterol = it.cholesterol,
				sodium = it.sodium,
				dietaryFiber = it.dietaryFiber,
				mealType = it.mealType,
				intakeAmount = it.intakeAmount
			)
		}
		foodLogRepository.saveAll(foodLogs)
	}
}