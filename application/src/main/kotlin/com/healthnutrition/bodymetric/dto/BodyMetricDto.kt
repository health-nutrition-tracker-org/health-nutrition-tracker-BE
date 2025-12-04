package com.healthnutrition.bodymetric.dto

import com.healthnutrition.bodymetric.ActivityLevel
import java.math.BigDecimal

class BodyMetricDto {
	data class Post(
		val accountId: Long,
		val height: BigDecimal,
		val weight: BigDecimal,
		val bodyFatRate: BigDecimal,
		val activityLevel: ActivityLevel
	)
}
