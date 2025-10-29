package com.healthnutrition.client.datago

import com.healthnutrition.client.datago.dto.DataGoClientRequest
import com.healthnutrition.client.datago.dto.DataGoClientResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class DataGoClient(
    @Qualifier("dataGoWebClient") private val webClient: WebClient
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Value("\${webclient.data-go.base-url}")
    private lateinit var baseUrl: String

    @Value("\${webclient.data-go.api-key}")
    private lateinit var apiKey: String

    fun getFoodNutritionInfo(
        foodNutritionRequest: DataGoClientRequest.FoodNutrition
    ): Mono<DataGoClientResponse.FoodNutrition> {
        return webClient.get()
            .uri("$baseUrl/getFoodNtrCpntDbInq02/?serviceKey=$apiKey&pageNo=${foodNutritionRequest.page}&numOfRows=${foodNutritionRequest.numOfRows}&type=json&FOOD_NM_KR=${foodNutritionRequest.foodName}")
            .retrieve()
            .bodyToMono(DataGoClientResponse.FoodNutrition::class.java)
            .onErrorResume { exception ->
                log.error("공공데이터포털 식품영양성분 조회 중 에러 발생", exception)
                Mono.error(exception)
            }
    }
}
