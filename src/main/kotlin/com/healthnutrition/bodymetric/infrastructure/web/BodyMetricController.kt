package com.healthnutrition.bodymetric.infrastructure.web

import com.healthnutrition.bodymetric.infrastructure.web.dto.BodyMetricRequest
import com.healthnutrition.bodymetric.usecase.BodyMetricUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BodyMetricController(
	private val bodyMetricUseCase: BodyMetricUseCase
) {
	@PostMapping("v1/body-metrics")
	fun postBodyMetric(
		@RequestAttribute accountId: Long,
		@RequestBody request: BodyMetricRequest.Post
	): ResponseEntity<Any> {
		bodyMetricUseCase.postBodyMetric(
			request = BodyMetricWebMapper.toPostDto(accountId, request)
		)
		return ResponseEntity.ok().build()
	}
}
