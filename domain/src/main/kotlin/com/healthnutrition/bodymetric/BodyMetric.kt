package com.healthnutrition.bodymetric

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
		return BigDecimal.valueOf(370).add(BigDecimal.valueOf(21.6).multiply(lbm))
			.setScale(2, RoundingMode.HALF_UP)
	}

	/**
	 * TDEE (일일 권장 섭취 칼로리) = BMR × 활동계수
	 */
	fun calculateTdee(): BigDecimal {
		return calculateBmr().multiply(activityLevel.factor).setScale(2, RoundingMode.HALF_UP)
	}

	/**
	 * 일일 권장 탄수화물양
	 * 총열량의 45-65%, 평균 55%
	 */
	fun calculateDailyCarbohydrate(): BigDecimal {
		return calculateTdee().multiply(BigDecimal.valueOf(0.55)).divide(BigDecimal.valueOf(4), 2, RoundingMode.HALF_UP)
	}

	/**
	 * 일일 권장 단백질
	 * 총열량의 10-35%, 평균 20%
	 */
	fun calculateDailyProtein(): BigDecimal {
		return calculateTdee().multiply(BigDecimal.valueOf(0.2)).divide(BigDecimal.valueOf(4), 2, RoundingMode.HALF_UP)
	}

	/**
	 * 일일 권장 지방
	 * 총열량의 20-35%, 평균 25%
	 */
	fun calculateDailyFat(): BigDecimal {
		return calculateTdee().multiply(BigDecimal.valueOf(0.25)).divide(BigDecimal.valueOf(9), 2, RoundingMode.HALF_UP)
	}
}
