package com.healthnutrition.dashboard.infrastructure.web

import com.healthnutrition.dashboard.infrastructure.web.dto.DashboardResponse
import com.healthnutrition.dashboard.usecase.dto.DashboardDto

object DashboardWebMapper {
	fun toKcalDiffInfoResponse(dto: DashboardDto.KcalDiffInfo): DashboardResponse.KcalDiffInfo =
		DashboardResponse.KcalDiffInfo(
			date = dto.date,
			tdee = dto.tdee,
			totalIntakeKcal = dto.totalIntakeKcal,
			diffKcal = dto.diffKcal
		)

	fun toIntakeNutritionInfoResponse(dto: DashboardDto.IntakeNutritionInfo): DashboardResponse.IntakeNutritionInfo =
		DashboardResponse.IntakeNutritionInfo(
			date = dto.date,
			totalCarbohydrate = dto.totalCarbohydrate,
			totalProtein = dto.totalProtein,
			totalFat = dto.totalFat,
			dailyCarbohydrate = dto.dailyCarbohydrate,
			dailyProtein = dto.dailyProtein,
			dailyFat = dto.dailyFat
		)
}