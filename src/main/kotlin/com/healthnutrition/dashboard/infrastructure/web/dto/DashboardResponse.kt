package com.healthnutrition.dashboard.infrastructure.web.dto

import java.math.BigDecimal

class DashboardResponse {
	data class KcalDiffInfo(
		val date: String, // 기준일자 (YYYY-MM-dd)
		val tdee: BigDecimal, // TDEE (일일 권장 섭취 칼로리)
		val totalIntakeKcal: BigDecimal, // 섭취한 칼로리
		val diffKcal: BigDecimal // 일일 권장 섭취 칼로리 - 섭취한 칼로리
	)
}