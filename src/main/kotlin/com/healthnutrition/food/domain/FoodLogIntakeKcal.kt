package com.healthnutrition.food.domain

import java.math.BigDecimal
import java.math.RoundingMode

data class FoodLogIntakeKcal(
	val accountId: Long,
	val servingSize: Int, // 영양성분함량기준량 (g)
	val kcal: BigDecimal, // 칼로리 (kcal)
	val intakeAmount: Int // 섭취량 (g)
) {
	/**
	 * 섭취한 칼로리 계산
	 * 섭취한 그램 양 당 칼로리
	 */
	fun calculateIntakeKcal(): BigDecimal {
		// (칼로리 / 영양성분함량기준량) * 섭취량
		return (kcal.divide(servingSize.toBigDecimal(), 2, RoundingMode.HALF_UP)).multiply(intakeAmount.toBigDecimal())
	}
}
