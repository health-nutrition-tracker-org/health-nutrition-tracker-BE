package com.healthnutrition.food

class FoodNutritionData {
	data class FoodNutrition(
		val body: FoodNutritionBody
	)

	data class FoodNutritionBody(
		val items: List<FoodNutritionBodyItem>,
		val numOfRows: String, // 한 페이지 결과수
		val page: String, // 페이지 번호
		val totalCount: String // 전체 결과 수
	)

	data class FoodNutritionBodyItem(
		val foodCode: String, // 식품코드
		val foodName: String, // 식품명
		val foodGroupCode: String, // 데이터구분코드
		val foodGroupName: String, // 데이터구분명
		val foodCategoryCode: String, // 식품대분류코드
		val foodCategoryName: String, // 식품대분류명
		val foodRepresentName: String, // 대표식품명
		val foodMidCategoryName: String?, // 식품중분류명
		val servingSize: String, // 영양성분함량기준량 (ex. 100g)
		val kcal: String, // 칼로리(kcal)
		val carbohydrate: String, // 탄수화물(g)
		val sugar: String, // 당류(g)
		val protein: String, // 단백질(g)
		val fat: String, // 지방(g)
		val saturatedFattyAcid: String, // 포화지방산(g)
		val transFattyAcid: String, // 트랜스지방산(g)
		val cholesterol: String, // 콜레스테롤(mg)
		val sodium: String, // 나트륨(mg)
		val dietaryFiber: String // 식이섬유(g)
	) {
		fun fetchServingSize(): Int {
			// 문자열에서 숫자만 추출
			val numberString = servingSize.filter { it.isDigit() }

			// 숫자가 하나라도 있으면 Int로 변환, 없으면 영양성분함량기준량 기본값 100(g) 반환
			return numberString.toIntOrNull() ?: 100
		}
	}
}