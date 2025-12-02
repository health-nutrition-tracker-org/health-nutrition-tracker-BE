package com.healthnutrition.food.infrastructure.persistence

import com.healthnutrition.food.domain.FoodLogIntakeKcal
import com.healthnutrition.food.usecase.dto.FoodDto

object FoodMapper {
	fun toLogEntity(accountId: Long, dto: FoodDto.CreateLog): FoodLogEntity = FoodLogEntity(
		accountId = accountId,
		foodName = dto.foodName,
		servingSize = dto.servingSize,
		kcal = dto.kcal,
		carbohydrate = dto.carbohydrate,
		sugar = dto.sugar,
		protein = dto.protein,
		fat = dto.fat,
		saturatedFattyAcid = dto.saturatedFattyAcid,
		transFattyAcid = dto.transFattyAcid,
		cholesterol = dto.cholesterol,
		sodium = dto.sodium,
		dietaryFiber = dto.dietaryFiber,
		intakeAmount = dto.intakeAmount,
		mealType = dto.mealType
	)

	fun toIntakeKcalDomain(entity: FoodLogEntity): FoodLogIntakeKcal = FoodLogIntakeKcal(
		accountId = entity.accountId,
		servingSize = entity.servingSize,
		intakeAmount = entity.intakeAmount,
		kcal = entity.kcal
	)
}