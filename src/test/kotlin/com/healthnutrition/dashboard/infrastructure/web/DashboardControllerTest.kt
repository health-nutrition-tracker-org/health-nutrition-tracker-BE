package com.healthnutrition.dashboard.infrastructure.web

import com.healthnutrition.MockMvcTest
import com.healthnutrition.auth.infrastructure.config.SecurityConfig
import com.healthnutrition.auth.infrastructure.security.JwtAuthFilter
import com.healthnutrition.dashboard.usecase.DashboardUseCase
import com.healthnutrition.dashboard.usecase.dto.DashboardDto
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
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigDecimal

@WebMvcTest(
	controllers = [DashboardController::class],
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
class DashboardControllerTest : MockMvcTest() {
	@MockkBean
	private lateinit var dashboardUseCase: DashboardUseCase

	@Test
	@DisplayName("특정일에 섭취한 칼로리 vs 일일 권장 섭취 칼로리 대시보드 정보 조회 테스트")
	fun getKcalDiffInfo_mock_test() {
		val date = "2025-11-26"
		val response = DashboardDto.KcalDiffInfo(
			date = date,
			tdee = BigDecimal.valueOf(2100.0),
			totalIntakeKcal = BigDecimal.valueOf(2080.0),
			diffKcal = BigDecimal.valueOf(20.0)
		)

		every { dashboardUseCase.getKcalDiffInfoByDate(any(), any()) } returns response

		mockMvc.perform(
			RestDocumentationRequestBuilders.get("/v1/dashboard/kcal-diff")
				.requestAttr("accountId", 1L)
				.queryParam("date", date)
				.accept(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers.status().isOk)
			.andDo(
				MockMvcRestDocumentation.document(
					"Get-Kcal-Diff-Info",
					RestDocUtil.requestPreprocessor(),
					RestDocUtil.responsePreprocessor(),
					RequestDocumentation.queryParameters(
						RequestDocumentation.parameterWithName("date").description("기준일자")
					),
					PayloadDocumentation.responseFields(
						PayloadDocumentation.fieldWithPath("date").type(JsonFieldType.STRING).description("기준일자"),
						PayloadDocumentation.fieldWithPath("tdee").type(BigDecimal::class.java).description("일일 권장 섭취 칼로리"),
						PayloadDocumentation.fieldWithPath("totalIntakeKcal").type(BigDecimal::class.java).description("기준일자에 섭취한 총 칼로리"),
						PayloadDocumentation.fieldWithPath("diffKcal").type(BigDecimal::class.java).description("일일 권장 섭취 칼로리 - 섭취한 칼로리"),
					)
				)
			)
	}

	@Test
	@DisplayName("특정일에 섭취한 탄수화물/단백질/지방 섭취량 대시보드 정보 조회 테스트")
	fun getIntakeNutrition_mock_test() {
		val date = "2025-11-26"
		val response = DashboardDto.IntakeNutritionDaily(
			date = date,
			totalCarbohydrate = BigDecimal.valueOf(169),
			totalProtein = BigDecimal.valueOf(70),
			totalFat = BigDecimal.valueOf(38),
			dailyCarbohydrate = BigDecimal.valueOf(243),
			dailyProtein = BigDecimal.valueOf(70),
			dailyFat = BigDecimal.valueOf(37)
		)

		every { dashboardUseCase.getIntakeNutritionByDate(any(), any()) } returns response

		mockMvc.perform(
			RestDocumentationRequestBuilders.get("/v1/dashboard/intake-nutrition")
				.requestAttr("accountId", 1L)
				.queryParam("date", date)
				.accept(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers.status().isOk)
			.andDo(
				MockMvcRestDocumentation.document(
					"Get-Intake-Nutrition-Info",
					RestDocUtil.requestPreprocessor(),
					RestDocUtil.responsePreprocessor(),
					RequestDocumentation.queryParameters(
						RequestDocumentation.parameterWithName("date").description("기준일자")
					),
					PayloadDocumentation.responseFields(
						PayloadDocumentation.fieldWithPath("date").type(JsonFieldType.STRING).description("기준일자"),
						PayloadDocumentation.fieldWithPath("totalCarbohydrate").type(BigDecimal::class.java).description("총 섭취 탄수화물"),
						PayloadDocumentation.fieldWithPath("totalProtein").type(BigDecimal::class.java).description("총 섭취 단백질"),
						PayloadDocumentation.fieldWithPath("totalFat").type(BigDecimal::class.java).description("총 섭취 지방"),
						PayloadDocumentation.fieldWithPath("dailyCarbohydrate").type(BigDecimal::class.java).description("일일 권장 탄수화물 섭취량"),
						PayloadDocumentation.fieldWithPath("dailyProtein").type(BigDecimal::class.java).description("일일 권장 단백질 섭취량"),
						PayloadDocumentation.fieldWithPath("dailyFat").type(BigDecimal::class.java).description("일일 권장 지방 섭취량")
					)
				)
			)
	}

	@Test
	@DisplayName("특정 기간동안 섭취한 탄수화물/단백질/지방 섭취량 대시보드 정보 조회 테스트")
	fun getIntakeSectionNutrition_mock_test() {
		val startDate = "2025-11-26"
		val endDate = "2025-11-30"
		val response = DashboardDto.IntakeNutritionSection(
			startDate = startDate,
			endDate = endDate,
			totalCarbohydrate = BigDecimal.valueOf(665),
			totalProtein = BigDecimal.valueOf(280),
			totalFat = BigDecimal.valueOf(135)
		)

		every { dashboardUseCase.getIntakeNutritionBetweenDate(any(), any(), any()) } returns response

		mockMvc.perform(
			RestDocumentationRequestBuilders.get("/v1/dashboard/intake-section-nutrition")
				.requestAttr("accountId", 1L)
				.queryParam("startDate", startDate)
				.queryParam("endDate", endDate)
				.accept(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers.status().isOk)
			.andDo(
				MockMvcRestDocumentation.document(
					"Get-Intake-Section-Nutrition-Info",
					RestDocUtil.requestPreprocessor(),
					RestDocUtil.responsePreprocessor(),
					RequestDocumentation.queryParameters(
						RequestDocumentation.parameterWithName("startDate").description("시작일자"),
						RequestDocumentation.parameterWithName("endDate").description("종료일자")
					),
					PayloadDocumentation.responseFields(
						PayloadDocumentation.fieldWithPath("startDate").type(JsonFieldType.STRING).description("시작일자"),
						PayloadDocumentation.fieldWithPath("endDate").type(JsonFieldType.STRING).description("종료일자"),
						PayloadDocumentation.fieldWithPath("totalCarbohydrate").type(BigDecimal::class.java).description("총 섭취 탄수화물"),
						PayloadDocumentation.fieldWithPath("totalProtein").type(BigDecimal::class.java).description("총 섭취 단백질"),
						PayloadDocumentation.fieldWithPath("totalFat").type(BigDecimal::class.java).description("총 섭취 지방")
					)
				)
			)
	}
}
