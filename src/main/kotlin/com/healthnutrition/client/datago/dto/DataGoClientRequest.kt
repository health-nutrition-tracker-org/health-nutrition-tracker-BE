package com.healthnutrition.client.datago.dto

class DataGoClientRequest {
    data class FoodNutrition(
        val page: Int, // 페이지 번호
        val numOfRows: Int, // 한 페이지 결과수
        val foodName: String // 식품명
    )
}