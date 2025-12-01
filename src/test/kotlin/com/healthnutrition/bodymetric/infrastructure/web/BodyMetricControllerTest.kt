package com.healthnutrition.bodymetric.infrastructure.web

import com.healthnutrition.MockMvcTest
import com.healthnutrition.auth.infrastructure.config.SecurityConfig
import com.healthnutrition.auth.infrastructure.security.JwtAuthFilter
import com.healthnutrition.bodymetric.domain.ActivityLevel
import com.healthnutrition.bodymetric.infrastructure.web.dto.BodyMetricRequest
import com.healthnutrition.bodymetric.usecase.BodyMetricUseCase
import com.healthnutrition.global.util.RestDocUtil
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigDecimal

@WebMvcTest(
	controllers = [BodyMetricController::class],
	excludeFilters = [
		ComponentScan.Filter(
			type = FilterType.ASSIGNABLE_TYPE,
			classes = [SecurityConfig::class, JwtAuthFilter::class]
		)
	],
	excludeAutoConfiguration = [
		SecurityAutoConfiguration::class,
		SecurityFilterAutoConfiguration::class
	]
)
class BodyMetricControllerTest : MockMvcTest() {
	@MockkBean
	private lateinit var bodyMetricUseCase: BodyMetricUseCase

	@Test
	@DisplayName("사용자 신체정보 저장 테스트")
	fun postBodyMetric_mock_test() {
		val request = BodyMetricRequest.Post(
			height = BigDecimal.valueOf(180.0),
			weight = BigDecimal.valueOf(80.0),
			bodyFatRate = BigDecimal.valueOf(25.0),
			activityLevel = ActivityLevel.MODERATE
		)

		every { bodyMetricUseCase.postBodyMetric(any()) } returns Unit

		mockMvc.perform(
			RestDocumentationRequestBuilders.post("/v1/body-metrics")
				.requestAttr("accountId", 1L)
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers.status().isOk)
			.andDo(
				MockMvcRestDocumentation.document(
					"Post-Body-Metric",
					RestDocUtil.requestPreprocessor(),
					RestDocUtil.responsePreprocessor(),
					PayloadDocumentation.requestFields(
						PayloadDocumentation.fieldWithPath("height").type(BigDecimal::class.java).description("키 (cm)"),
						PayloadDocumentation.fieldWithPath("weight").type(BigDecimal::class.java).description("몸무게 (kg)"),
						PayloadDocumentation.fieldWithPath("bodyFatRate").type(BigDecimal::class.java).description("체지방률 (%)"),
						PayloadDocumentation.fieldWithPath("activityLevel").type(JsonFieldType.STRING).description("활동수준")
					)
				)
			)
	}
}
