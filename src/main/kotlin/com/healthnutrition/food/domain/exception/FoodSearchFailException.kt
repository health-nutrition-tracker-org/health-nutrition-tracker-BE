package com.healthnutrition.food.domain.exception

import com.healthnutrition.global.domain.DomainException
import org.springframework.http.HttpStatus

open class FoodSearchFailException(
	override val status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
	override var message: String = "음식 검색 중 예외가 발생했습니다."
) : DomainException()
