package com.healthnutrition.dashboard.infrastructure.web

import com.healthnutrition.MockMvcTest
import com.healthnutrition.auth.infrastructure.config.SecurityConfig
import com.healthnutrition.auth.infrastructure.security.JwtAuthFilter
import com.healthnutrition.dashboard.usecase.DashboardUseCase
import com.healthnutrition.dashboard.usecase.dto.DashboardDto
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
import org.springframework.restdocs.operation.preprocess.Preprocessors
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
			date = "2025-11-26",
			tdee = BigDecimal.valueOf(2100.0),
			totalIntakeKcal = BigDecimal.valueOf(2080.0),
			diffKcal = BigDecimal.valueOf(20.0)
		)

		every { dashboardUseCase.getKcalDiffInfo(any(), any()) } returns response

		mockMvc.perform(
			RestDocumentationRequestBuilders.get("/v1/dashboard/kcal-diff")
				.requestAttr("accountId", 1L)
				.queryParam("date", date)
				.accept(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers.status().isOk)
			.andDo(
				MockMvcRestDocumentation.document(
					"Get-Kcal-Diff-Info",
					Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
					Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
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
}
