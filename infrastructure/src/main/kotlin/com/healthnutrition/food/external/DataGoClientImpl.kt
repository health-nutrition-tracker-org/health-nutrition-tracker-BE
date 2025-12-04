package com.healthnutrition.food.external

import com.healthnutrition.food.DataGoClient
import com.healthnutrition.food.FoodMapper
import com.healthnutrition.food.FoodNutritionData
import com.healthnutrition.food.exception.FoodSearchFailException
import com.healthnutrition.food.external.dto.DataGoClientResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class DataGoClientImpl(
	@Qualifier("dataGoWebClient") private val webClient: WebClient
) : DataGoClient {
	private val log = LoggerFactory.getLogger(javaClass)

	@Value("\${webclient.data-go.base-url}")
	private lateinit var baseUrl: String

	@Value("\${webclient.data-go.api-key}")
	private lateinit var apiKey: String

	override fun getFoodNutritionData(
		page: Int,
		numOfRows: Int,
		foodName: String
	): FoodNutritionData.FoodNutrition {
		val responseNutrition = webClient.get()
			.uri("$baseUrl/getFoodNtrCpntDbInq02?serviceKey=$apiKey&pageNo=${page}&numOfRows=${numOfRows}&type=json&FOOD_NM_KR=${foodName}")
			.retrieve()
			.bodyToMono(DataGoClientResponse.FoodNutrition::class.java)
			.onErrorResume { exception ->
				log.error("공공데이터포털 식품영양성분 조회 중 에러 발생", exception)
				Mono.error(exception)
			}.block() ?: run { throw FoodSearchFailException() }

		return FoodMapper.toNutritionData(responseNutrition)
	}
}