package com.healthnutrition.food.exception

import com.healthnutrition.DomainException

open class FoodSearchFailException(
	message: String = "음식 검색 중 예외가 발생했습니다."
) : DomainException(message = message, code = "FOOD_SEARCH_FAIL")
