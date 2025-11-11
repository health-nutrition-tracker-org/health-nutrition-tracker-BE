package com.healthnutrition.food.infrastructure.persistence

import com.healthnutrition.food.domain.MealType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.math.BigDecimal
import java.time.LocalDateTime

@EntityListeners(AuditingEntityListener::class)
@Entity
@Table(name = "food_log")
class FoodLogEntity(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null,

	@Column(name = "account_id")
	val accountId: Long,

	@Column(name = "food_name")
	val foodName: String,

	@Column(name = "serving_size")
	val servingSize: Int?, // 영양성분함량기준량 (g)

	@Column
	val kcal: BigDecimal, // 칼로리 (kcal)

	@Column
	val carbohydrate: BigDecimal, // 탄수화물 (g)

	@Column
	val sugar: BigDecimal, // 당류 (g)

	@Column
	val protein: BigDecimal, // 단백질 (g)

	@Column
	val fat: BigDecimal, // 지방 (g)

	@Column(name = "saturated_fatty_acid")
	val saturatedFattyAcid: BigDecimal?, // 포화지방산 (g)

	@Column(name = "trans_fatty_acid")
	val transFattyAcid: BigDecimal?, // 트랜스지방산 (g)

	@Column
	val cholesterol: BigDecimal, // 콜레스테롤 (mg)

	@Column
	val sodium: BigDecimal, // 나트륨 (mg)

	@Column(name = "dietary_fiber")
	val dietaryFiber: BigDecimal?, // 식이섬유 (g)

	@Column(name = "meal_type")
	@Enumerated(EnumType.STRING)
	val mealType: MealType, // 식사유형

	@CreatedDate
	@Column(name = "created_at")
	val createdAt: LocalDateTime? = null
)
