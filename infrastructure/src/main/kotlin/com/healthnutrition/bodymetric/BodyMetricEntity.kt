package com.healthnutrition.bodymetric

import com.healthnutrition.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "body_metric")
class BodyMetricEntity(
	@Column(name = "account_id")
	val accountId: Long,

	@Column
	val height: BigDecimal, // 키 (cm)

	@Column
	val weight: BigDecimal, // 몸무게 (kg)

	@Column(name = "body_fat_rate")
	val bodyFatRate: BigDecimal, // 체지방률 (%)

	@Column(name = "activity_level")
	@Enumerated(EnumType.STRING)
	val activityLevel: ActivityLevel // 활동수준
) : BaseEntity()
