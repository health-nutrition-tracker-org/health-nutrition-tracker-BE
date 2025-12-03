package com.healthnutrition.dashboard

import com.healthnutrition.dashboard.dto.DashboardDto
import com.healthnutrition.dashboard.dto.DashboardResponse

object DashboardWebMapper {
	fun toKcalDiffInfoResponse(dto: DashboardDto.KcalDiffInfo): DashboardResponse.KcalDiffInfo =
		DashboardResponse.KcalDiffInfo(
			date = dto.date,
			tdee = dto.tdee,
			totalIntakeKcal = dto.totalIntakeKcal,
			diffKcal = dto.diffKcal
		)

	fun toIntakeNutritionDailyResponse(dto: DashboardDto.IntakeNutritionDaily): DashboardResponse.IntakeNutritionDaily =
		DashboardResponse.IntakeNutritionDaily(
			date = dto.date,
			totalCarbohydrate = dto.totalCarbohydrate,
			totalProtein = dto.totalProtein,
			totalFat = dto.totalFat,
			dailyCarbohydrate = dto.dailyCarbohydrate,
			dailyProtein = dto.dailyProtein,
			dailyFat = dto.dailyFat
		)

	fun toIntakeNutritionSectionResponse(dto: DashboardDto.IntakeNutritionSection): DashboardResponse.IntakeNutritionSection =
		DashboardResponse.IntakeNutritionSection(
			startDate = dto.startDate,
			endDate = dto.endDate,
			totalCarbohydrate = dto.totalCarbohydrate,
			totalProtein = dto.totalProtein,
			totalFat = dto.totalFat
		)
}