package com.healthnutrition.food.dto

import com.healthnutrition.food.MealType
import java.math.BigDecimal

class FoodRequest {
	data class CreateLog(
		val items: List<CreateLogItem>
	)

	data class CreateLogItem(
		val foodName: String,
		val servingSize: Int,
		val kcal: BigDecimal,
		val carbohydrate: BigDecimal,
		val sugar: BigDecimal,
		val protein: BigDecimal,
		val fat: BigDecimal,
		val saturatedFattyAcid: BigDecimal?,
		val transFattyAcid: BigDecimal?,
		val cholesterol: BigDecimal,
		val sodium: BigDecimal,
		val dietaryFiber: BigDecimal?,
		val intakeAmount: Int,
		val mealType: MealType
	)
}