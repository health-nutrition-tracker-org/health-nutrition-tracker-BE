package com.healthnutrition.food.usecase.dto

import java.math.BigDecimal

class FoodDto {
	data class Search(
		val foodName: String,
		val foodCategoryName: String, // 식품 대분류명
		val servingSize: Int?, // 영양성분함량기준량 (g)
		val kcal: BigDecimal, // 칼로리 (kcal)
		val carbohydrate: BigDecimal, // 탄수화물 (g)
		val sugar: BigDecimal, // 당류 (g)
		val protein: BigDecimal, // 단백질 (g)
		val fat: BigDecimal, // 지방 (g)
		val saturatedFattyAcid: BigDecimal?, // 포화지방산 (g)
		val transFattyAcid: BigDecimal?, // 트랜스지방산 (g)
		val cholesterol: BigDecimal, // 콜레스테롤 (mg)
		val sodium: BigDecimal, // 나트륨 (mg)
		val dietaryFiber: BigDecimal? // 식이섬유 (g)
	)
}
