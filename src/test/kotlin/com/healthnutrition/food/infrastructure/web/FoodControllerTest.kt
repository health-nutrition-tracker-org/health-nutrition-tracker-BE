package com.healthnutrition.food.infrastructure.web

import com.healthnutrition.MockMvcTest
import com.healthnutrition.auth.infrastructure.config.SecurityConfig
import com.healthnutrition.auth.infrastructure.security.JwtAuthFilter
import com.healthnutrition.food.usecase.FoodUseCase
import com.healthnutrition.food.usecase.dto.FoodDto
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
	controllers = [FoodController::class],
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
class FoodControllerTest : MockMvcTest() {
	@MockkBean
	private lateinit var foodUseCase: FoodUseCase

	@Test
	@DisplayName("음식 검색 테스트")
	fun searchFoods_mock_test() {
		val keyword = "볶음밥"
		val page = 1
		val numOfRows = 3
		val response = listOf(
			FoodDto.Search(
				foodName = "볶음밥",
				foodCategoryName = "밥류",
				servingSize = 100,
				kcal = BigDecimal.valueOf(183),
				carbohydrate = BigDecimal.valueOf(33.97),
				sugar = BigDecimal.valueOf(0),
				protein = BigDecimal.valueOf(5.56),
				fat = BigDecimal.valueOf(2.76),
				saturatedFattyAcid = BigDecimal.valueOf(1.1),
				transFattyAcid = BigDecimal.valueOf(0.02),
				cholesterol = BigDecimal.valueOf(29.28),
				sodium = BigDecimal.valueOf(212),
				dietaryFiber = BigDecimal.valueOf(1.7)
			),
			FoodDto.Search(
				foodName = "계란_볶음밥",
				foodCategoryName = "밥류",
				servingSize = 100,
				kcal = BigDecimal.valueOf(225),
				carbohydrate = BigDecimal.valueOf(24.23),
				sugar = BigDecimal.valueOf(0.17),
				protein = BigDecimal.valueOf(6.62),
				fat = BigDecimal.valueOf(11.2),
				saturatedFattyAcid = BigDecimal.valueOf(2.2),
				transFattyAcid = BigDecimal.valueOf(0.05),
				cholesterol = BigDecimal.valueOf(62.63),
				sodium = BigDecimal.valueOf(318),
				dietaryFiber = BigDecimal.valueOf(1.8)
			),
			FoodDto.Search(
				foodName = "김치_볶음밥",
				foodCategoryName = "밥류",
				servingSize = 100,
				kcal = BigDecimal.valueOf(167),
				carbohydrate = BigDecimal.valueOf(15.74),
				sugar = BigDecimal.valueOf(13.8),
				protein = BigDecimal.valueOf(5.32),
				fat = BigDecimal.valueOf(9.8),
				saturatedFattyAcid = BigDecimal.valueOf(1.43),
				transFattyAcid = BigDecimal.valueOf(0.04),
				cholesterol = BigDecimal.valueOf(70.41),
				sodium = BigDecimal.valueOf(470),
				dietaryFiber = BigDecimal.valueOf(2.8)
			)
		)

		every { foodUseCase.searchFoods(any(), any(), any()) } returns response

		mockMvc.perform(
			RestDocumentationRequestBuilders.get("/v1/foods")
				.requestAttr("accountId", 1L)
				.queryParam("keyword", keyword)
				.queryParam("page", page.toString())
				.queryParam("numOfRows", numOfRows.toString())
				.accept(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers.status().isOk)
			.andDo(
				MockMvcRestDocumentation.document(
					"Search-Food",
					Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
					Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
					RequestDocumentation.queryParameters(
						RequestDocumentation.parameterWithName("keyword").description("검색 키워드"),
						RequestDocumentation.parameterWithName("page").description("페이지 번호"),
						RequestDocumentation.parameterWithName("numOfRows").description("검색 아이템 수")
					),
					PayloadDocumentation.responseFields(
						PayloadDocumentation.fieldWithPath("items").type(JsonFieldType.ARRAY).description("음식 검색 결과"),
						PayloadDocumentation.fieldWithPath("items[].foodName").type(JsonFieldType.STRING).description("음식명"),
						PayloadDocumentation.fieldWithPath("items[].foodCategoryName").type(JsonFieldType.STRING).description("식품 대분류명"),
						PayloadDocumentation.fieldWithPath("items[].servingSize").type(JsonFieldType.NUMBER).description("영양성분함량기준량 (g)"),
						PayloadDocumentation.fieldWithPath("items[].kcal").type(JsonFieldType.VARIES).description("칼로리 (kcal)"),
						PayloadDocumentation.fieldWithPath("items[].carbohydrate").type(JsonFieldType.VARIES).description("탄수화물 (g)"),
						PayloadDocumentation.fieldWithPath("items[].sugar").type(JsonFieldType.VARIES).description("당류 (g)"),
						PayloadDocumentation.fieldWithPath("items[].protein").type(JsonFieldType.VARIES).description("프로틴 (g)"),
						PayloadDocumentation.fieldWithPath("items[].fat").type(JsonFieldType.VARIES).description("지방 (g)"),
						PayloadDocumentation.fieldWithPath("items[].saturatedFattyAcid").type(JsonFieldType.VARIES).description("포화지방산 (g) (Nullable)"),
						PayloadDocumentation.fieldWithPath("items[].transFattyAcid").type(JsonFieldType.VARIES).description("트랜스지방산 (g) (Nullable)"),
						PayloadDocumentation.fieldWithPath("items[].cholesterol").type(JsonFieldType.VARIES).description("콜레스테롤 (mg)"),
						PayloadDocumentation.fieldWithPath("items[].sodium").type(JsonFieldType.VARIES).description("나트륨 (mg)"),
						PayloadDocumentation.fieldWithPath("items[].dietaryFiber").type(JsonFieldType.VARIES).description("식이섬유 (g) (Nullable)"),
					)
				)
			)
	}
}
