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
	fun getDashboard(
		@RequestAttribute accountId: Long,
		@RequestParam("date") date: String
	): ResponseEntity<DashboardResponse.KcalDiffInfo> {
		return ResponseEntity.ok(
			DashboardWebMapper.toKcalDiffInfoResponse(
				dto = dashboardUseCase.getKcalDiffInfo(accountId, date)
			)
		)
	}
}
