package com.healthnutrition.food.infrastructure.web

import com.healthnutrition.food.infrastructure.web.dto.FoodResponse
import com.healthnutrition.food.usecase.dto.FoodDto

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
}
