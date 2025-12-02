package com.healthnutrition.bodymetric.infrastructure.web.dto

import com.healthnutrition.bodymetric.domain.ActivityLevel
import java.math.BigDecimal

class BodyMetricRequest {
	data class Post(
		val height: BigDecimal,
		val weight: BigDecimal,
		val bodyFatRate: BigDecimal,
		val activityLevel: ActivityLevel
	)
}