package com.healthnutrition.food

import com.healthnutrition.food.dto.FoodDto
import com.healthnutrition.food.dto.FoodRequest
import com.healthnutrition.food.dto.FoodResponse

object FoodWebMapper {
	fun toFoodSearchResponse(foodSearchDtos: List<FoodDto.Search>): FoodResponse.Search {
		val searchItems = foodSearchDtos.map { foodDto ->
			FoodResponse.SearchItem(
				foodName = foodDto.foodName,
				foodCategoryName = foodDto.foodCategoryName,
				servingSize = foodDto.servingSize,
				kcal = foodDto.kcal,
				carbohydrate = foodDto.carbohydrate,
				sugar = foodDto.sugar,
				protein = foodDto.protein,
				fat = foodDto.fat,
				saturatedFattyAcid = foodDto.saturatedFattyAcid,
				transFattyAcid = foodDto.transFattyAcid,
				cholesterol = foodDto.cholesterol,
				sodium = foodDto.sodium,
				dietaryFiber = foodDto.dietaryFiber
			)
		}

		return FoodResponse.Search(items = searchItems)
	}

	fun toFoodLogCreateDtos(createFoodLogRequest: FoodRequest.CreateLog): List<FoodDto.CreateLog> {
		return createFoodLogRequest.items.map { request ->
			FoodDto.CreateLog(
				foodName = request.foodName,
				servingSize = request.servingSize,
				kcal = request.kcal,
				carbohydrate = request.carbohydrate,
				sugar = request.sugar,
				protein = request.protein,
				fat = request.fat,
				saturatedFattyAcid = request.saturatedFattyAcid,
				transFattyAcid = request.transFattyAcid,
				cholesterol = request.cholesterol,
				sodium = request.sodium,
				dietaryFiber = request.dietaryFiber,
				intakeAmount = request.intakeAmount,
				mealType = request.mealType
			)
		}
	}
}
