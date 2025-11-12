package com.healthnutrition.food.infrastructure.web

import com.healthnutrition.food.infrastructure.web.dto.FoodResponse
import com.healthnutrition.food.usecase.FoodUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class FoodController(
	private val foodUseCase: FoodUseCase
) {
	@GetMapping("v1/foods")
	fun searchFoods(
		@RequestAttribute accountId: Long,
		@RequestParam("keyword") keyword: String,
		@RequestParam("page") page: Int,
		@RequestParam("numOfRows") numOfRows: Int
	): ResponseEntity<FoodResponse.Search> {
		return ResponseEntity.ok(
			FoodWebMapper.toFoodSearchResponse(
				foodSearchDtos = foodUseCase.searchFoods(keyword, page, numOfRows)
			)
		)
	}
}