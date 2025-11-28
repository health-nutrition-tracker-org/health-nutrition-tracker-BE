package com.healthnutrition.bodymetric.domain

import java.math.BigDecimal
import java.math.RoundingMode

data class BodyMetric(
	val accountId: Long,
	val height: BigDecimal,
	val weight: BigDecimal,
	val bodyFatRate: BigDecimal,
	val activityLevel: ActivityLevel
) {
	/**
	 * LBM (제지방량) = 체중(kg) × (1 - 체지방률(%))
	 * BMR (기초대사량) = 370 + (21.6 × LBM)
	 */
	fun calculateBmr(): BigDecimal {
		val bodyFatPercent = bodyFatRate.divide(BigDecimal.valueOf(100))
		val lbm = weight.multiply(BigDecimal.ONE.subtract(bodyFatPercent))
			.setScale(2, RoundingMode.HALF_UP)
		return BigDecimal.valueOf(370).plus(BigDecimal.valueOf(21.6).multiply(lbm))
			.setScale(2, RoundingMode.HALF_UP)
	}

	/**
	 * TDEE (일일 권장 섭취 칼로리) = BMR × 활동계수
	 */
	fun calculateTdee(): BigDecimal {
		return calculateBmr().multiply(activityLevel.factor).setScale(2, RoundingMode.HALF_UP)
	}
}
