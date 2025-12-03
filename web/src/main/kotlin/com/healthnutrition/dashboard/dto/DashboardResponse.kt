package com.healthnutrition.dashboard.dto

import java.math.BigDecimal

class DashboardResponse {
	data class KcalDiffInfo(
		val date: String, // 기준일자 (YYYY-MM-dd)
		val tdee: BigDecimal, // TDEE (일일 권장 섭취 칼로리)
		val totalIntakeKcal: BigDecimal, // 섭취한 총 칼로리
		val diffKcal: BigDecimal // 일일 권장 섭취 칼로리 - 섭취한 칼로리
	)

	data class IntakeNutritionDaily(
		val date: String, // 기준일자 (YYYY-MM-dd)
		val totalCarbohydrate: BigDecimal, // 총 탄수화물 양
		val totalProtein: BigDecimal, // 총 단백질 양
		val totalFat: BigDecimal, // 총 지방 양
		val dailyCarbohydrate: BigDecimal, // 일일 권장 탄수화물 섭취량
		val dailyProtein: BigDecimal, // 일일 권장 단백질 섭취량
		val dailyFat: BigDecimal // 일일 권장 지방 섭취량
	)

	data class IntakeNutritionSection(
		val startDate: String, // 시작일자 (YYYY-MM-dd)
		val endDate: String, // 종료일자 (YYYY-MM-dd)
		val totalCarbohydrate: BigDecimal, // 총 탄수화물 양
		val totalProtein: BigDecimal, // 총 단백질 양
		val totalFat: BigDecimal // 총 지방 양
	)
}