package com.healthnutrition.dashboard.infrastructure.web

import com.healthnutrition.dashboard.infrastructure.web.dto.DashboardResponse
import com.healthnutrition.dashboard.usecase.DashboardUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class DashboardController(
	private val dashboardUseCase: DashboardUseCase
) {
	@GetMapping("v1/dashboard/kcal-diff")
	fun getKcalDiffInfo(
		@RequestAttribute accountId: Long,
		@RequestParam("date") date: String
	): ResponseEntity<DashboardResponse.KcalDiffInfo> {
		return ResponseEntity.ok(
			DashboardWebMapper.toKcalDiffInfoResponse(
				dto = dashboardUseCase.getKcalDiffInfoByDate(accountId, date)
			)
		)
	}

	@GetMapping("v1/dashboard/intake-nutrition")
	fun getIntakeNutrition(
		@RequestAttribute accountId: Long,
		@RequestParam("date") date: String
	): ResponseEntity<DashboardResponse.IntakeNutritionDaily> {
		return ResponseEntity.ok(
			DashboardWebMapper.toIntakeNutritionDailyResponse(
				dto = dashboardUseCase.getIntakeNutritionByDate(accountId, date)
			)
		)
	}

	@GetMapping("v1/dashboard/intake-section-nutrition")
	fun getIntakeSectionNutrition(
		@RequestAttribute accountId: Long,
		@RequestParam("startDate") startDate: String,
		@RequestParam("endDate") endDate: String
	): ResponseEntity<DashboardResponse.IntakeNutritionSection> {
		return ResponseEntity.ok(
			DashboardWebMapper.toIntakeNutritionSectionResponse(
				dto = dashboardUseCase.getIntakeNutritionBetweenDate(accountId, startDate, endDate)
			)
		)
	}
}
