package com.healthnutrition.food

interface DataGoClient {
	fun getFoodNutritionData(
		page: Int, // 페이지 번호
		numOfRows: Int, // 한 페이지 결과수
		foodName: String // 식품명
	): FoodNutritionSearchData.FoodNutritionSearch
}