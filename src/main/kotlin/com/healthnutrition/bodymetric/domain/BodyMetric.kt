package com.healthnutrition.bodymetric.domain

import java.math.BigDecimal

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
		val lbm = weight * (BigDecimal.ONE - bodyFatRate)
		return BigDecimal.valueOf(370) + (BigDecimal.valueOf(21.6) * lbm)
	}

	/**
	 * TDEE (일일 권장 섭취 칼로리) = BMR × 활동계수
	 */
	fun calculateTdee(): BigDecimal {
		return calculateBmr() * activityLevel.factor
	}
}
