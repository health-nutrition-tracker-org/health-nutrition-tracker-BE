package com.healthnutrition.food.infrastructure.external.dto

import com.fasterxml.jackson.annotation.JsonProperty

class DataGoClientResponse {
    data class FoodNutrition(
        @JsonProperty("body") val body: FoodNutritionBody
    )

    data class FoodNutritionBody(
        @JsonProperty("items") val items: List<FoodNutritionBodyItem>,
        @JsonProperty("numOfRows") val numOfRows: String, // 한 페이지 결과수
        @JsonProperty("pageNo") val page: String, // 페이지 번호
        @JsonProperty("totalCount") val totalCount: String // 전체 결과 수
    )

    data class FoodNutritionBodyItem(
        @JsonProperty("FOOD_CD") val foodCode: String, // 식품코드
        @JsonProperty("FOOD_NM_KR") val foodName: String, // 식품명
        @JsonProperty("DB_GRP_CM") val foodGroupCode: String, // 데이터구분코드
        @JsonProperty("DB_GRP_NM") val foodGroupName: String, // 데이터구분명
        @JsonProperty("FOOD_CAT1_CD") val foodCategoryCode: String, // 식품대분류코드
        @JsonProperty("FOOD_CAT1_NM") val foodCategoryName: String, // 식품대분류명
        @JsonProperty("FOOD_REF_NM") val foodRepresentName: String, // 대표식품명
        @JsonProperty("FOOD_CAT2_NM") val foodMidCategoryName: String?, // 식품중분류명
        @JsonProperty("SERVING_SIZE") val servingSize: String, // 영양성분함량기준량 (ex. 100g)
        @JsonProperty("AMT_NUM1") val kcal: String, // 칼로리(kcal)
        @JsonProperty("AMT_NUM6") val carbohydrate: String, // 탄수화물(g)
        @JsonProperty("AMT_NUM7") val sugar: String, // 당류(g)
        @JsonProperty("AMT_NUM3") val protein: String, // 단백질(g)
        @JsonProperty("AMT_NUM4") val fat: String, // 지방(g)
        @JsonProperty("AMT_NUM24") val saturatedFattyAcid: String, // 포화지방산(g)
        @JsonProperty("AMT_NUM25") val transFattyAcid: String, // 트랜스지방산(g)
        @JsonProperty("AMT_NUM23") val cholesterol: String, // 콜레스테롤(mg)
        @JsonProperty("AMT_NUM13") val sodium: String, // 나트륨(mg)
        @JsonProperty("AMT_NUM8") val dietaryFiber: String // 식이섬유(g)
    )
}
