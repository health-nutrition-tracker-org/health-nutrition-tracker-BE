package com.healthnutrition.food

import com.healthnutrition.food.external.dto.DataGoClientResponse

object FoodMapper {
	fun toNutritionData(response: DataGoClientResponse.FoodNutrition): FoodNutritionData.FoodNutrition = FoodNutritionData.FoodNutrition(
		body = FoodNutritionData.FoodNutritionBody(
			items = response.body.items.map {
				FoodNutritionData.FoodNutritionBodyItem(
					foodCode = it.foodCode,
					foodName = it.foodName,
					foodGroupCode = it.foodGroupCode,
					foodGroupName = it.foodGroupName,
					foodCategoryCode = it.foodCategoryCode,
					foodCategoryName = it.foodCategoryName,
					foodRepresentName = it.foodRepresentName,
					foodMidCategoryName = it.foodMidCategoryName,
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
					dietaryFiber = it.dietaryFiber
				)
			},
			numOfRows = response.body.numOfRows,
			page = response.body.page,
			totalCount = response.body.totalCount
		)
	)

	fun toLogEntity(domain: FoodLog): FoodLogEntity = FoodLogEntity(
		accountId = domain.accountId,
		foodName = domain.foodName,
		servingSize = domain.servingSize,
		kcal = domain.kcal,
		carbohydrate = domain.carbohydrate,
		sugar = domain.sugar,
		protein = domain.protein,
		fat = domain.fat,
		saturatedFattyAcid = domain.saturatedFattyAcid,
		transFattyAcid = domain.transFattyAcid,
		cholesterol = domain.cholesterol,
		sodium = domain.sodium,
		dietaryFiber = domain.dietaryFiber,
		intakeAmount = domain.intakeAmount,
		mealType = domain.mealType
	)

	fun toLogDomain(entity: FoodLogEntity): FoodLog = FoodLog(
		accountId = entity.accountId,
		foodName = entity.foodName,
		servingSize = entity.servingSize,
		intakeAmount = entity.intakeAmount,
		kcal = entity.kcal,
		carbohydrate = entity.carbohydrate,
		sugar = entity.sugar,
		protein = entity.protein,
		fat = entity.fat,
		saturatedFattyAcid = entity.saturatedFattyAcid,
		transFattyAcid = entity.transFattyAcid,
		cholesterol = entity.cholesterol,
		sodium = entity.sodium,
		dietaryFiber = entity.dietaryFiber,
		mealType = entity.mealType,
		createdAt = entity.createdAt
	)
}