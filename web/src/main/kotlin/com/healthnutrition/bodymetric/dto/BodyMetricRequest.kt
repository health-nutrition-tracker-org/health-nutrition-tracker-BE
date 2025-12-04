package com.healthnutrition.bodymetric.dto

import com.healthnutrition.bodymetric.ActivityLevel
import java.math.BigDecimal

class BodyMetricRequest {
	data class Post(
		val height: BigDecimal,
		val weight: BigDecimal,
		val bodyFatRate: BigDecimal,
		val activityLevel: ActivityLevel
	)
}