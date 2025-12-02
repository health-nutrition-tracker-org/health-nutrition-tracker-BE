package com.healthnutrition.dashboard.usecase

import com.healthnutrition.bodymetric.domain.BodyMetricRepository
import com.healthnutrition.bodymetric.infrastructure.persistence.BodyMetricMapper
import com.healthnutrition.dashboard.usecase.dto.DashboardDto
import com.healthnutrition.food.domain.FoodLogRepository
import com.healthnutrition.food.infrastructure.persistence.FoodMapper
import com.healthnutrition.global.util.DateUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.LocalTime
import java.time.ZoneId

@Service
class DashboardUseCase(
	private val bodyMetricRepository: BodyMetricRepository,
	private val foodLogRepository: FoodLogRepository
) {
    /**
     * 사용자 별 특정일 섭취한 칼로리 vs 일일 권장 섭취 칼로리
     */
    @Transactional(readOnly = true)
    fun getKcalDiffInfoByDate(accountId: Long, date: String): DashboardDto.KcalDiffInfo {
	    val targetDate = DateUtil.parseDate("yyyy-MM-dd", date)
		val startDate = targetDate.atStartOfDay(ZoneId.of("Asia/Seoul")).toLocalDateTime() // 오늘 하루의 시작 일자
	    val endDate = targetDate.atTime(LocalTime.MAX) // 오늘 하루의 끝 일자

	    val tdee = BodyMetricMapper.toDomain(entity = bodyMetricRepository.getByAccountIdOrThrow(accountId)).calculateTdee()
	    val totalIntakeKcal = foodLogRepository.getAllByAccountIdEqualsAndCreatedAtBetween(
		    accountId = accountId,
			startDate = startDate,
			endDate = endDate
		).map {
			FoodMapper.toIntakeKcalDomain(it)
	    }.sumOf { it.calculateIntakeKcal() } // 섭취한 칼로리 총 합 계산

	    return DashboardDto.KcalDiffInfo(
			date = date,
			tdee = tdee,
			totalIntakeKcal = totalIntakeKcal,
			diffKcal = tdee.subtract(totalIntakeKcal)
		)
	}

	/**
	 * 사용자 별 특정일의 탄수화물/지방/단백질 섭취량
	 */
	@Transactional(readOnly = true)
	fun getIntakeNutritionByDate(accountId: Long, date: String): DashboardDto.IntakeNutritionDaily {
		val targetDate = DateUtil.parseDate("yyyy-MM-dd", date)
		val startDate = targetDate.atStartOfDay(ZoneId.of("Asia/Seoul")).toLocalDateTime() // 오늘 하루의 시작 일자
		val endDate = targetDate.atTime(LocalTime.MAX) // 오늘 하루의 끝 일자

		val bodyMetric = BodyMetricMapper.toDomain(entity = bodyMetricRepository.getByAccountIdOrThrow(accountId))

		val foodLogEntities = foodLogRepository.getAllByAccountIdEqualsAndCreatedAtBetween(
			accountId = accountId,
			startDate = startDate,
			endDate = endDate
		)
		var totalCarbohydrate = BigDecimal.ZERO
		var totalProtein = BigDecimal.ZERO
		var totalFat = BigDecimal.ZERO

		foodLogEntities.forEach { foodLog ->
			totalCarbohydrate = totalCarbohydrate.add(foodLog.carbohydrate)
			totalProtein = totalProtein.add(foodLog.protein)
			totalFat = totalFat.add(foodLog.fat)
		}

		return DashboardDto.IntakeNutritionDaily(
			date = date,
			totalCarbohydrate = totalCarbohydrate,
			totalProtein = totalProtein,
			totalFat = totalFat,
			dailyCarbohydrate = bodyMetric.calculateDailyCarbohydrate(),
			dailyProtein = bodyMetric.calculateDailyProtein(),
			dailyFat = bodyMetric.calculateDailyFat()
		)
	}

	/**
	 * 사용자 별 특정 기간동안의 탄수화물/지방/단백질 섭취량
	 */
	@Transactional(readOnly = true)
	fun getIntakeNutritionBetweenDate(accountId: Long, startDate: String, endDate: String): DashboardDto.IntakeNutritionSection {
		val start = DateUtil.parseDate("yyyy-MM-dd", startDate).atStartOfDay(ZoneId.of("Asia/Seoul")).toLocalDateTime() // 시작일자의 시작 시간
		val end = DateUtil.parseDate("yyyy-MM-dd", endDate).atTime(LocalTime.MAX) // 종료일자의 하루 끝 시간

		val bodyMetric = BodyMetricMapper.toDomain(entity = bodyMetricRepository.getByAccountIdOrThrow(accountId))
		val foodLogEntities = foodLogRepository.getAllByAccountIdEqualsAndCreatedAtBetween(
			accountId = accountId,
			startDate = start,
			endDate = end
		)
		var totalCarbohydrate = BigDecimal.ZERO
		var totalProtein = BigDecimal.ZERO
		var totalFat = BigDecimal.ZERO

		foodLogEntities.forEach { foodLog ->
			totalCarbohydrate = totalCarbohydrate.add(foodLog.carbohydrate)
			totalProtein = totalProtein.add(foodLog.protein)
			totalFat = totalFat.add(foodLog.fat)
		}

		return DashboardDto.IntakeNutritionSection(
			startDate = startDate,
			endDate = endDate,
			totalCarbohydrate = totalCarbohydrate,
			totalProtein = totalProtein,
			totalFat = totalFat
		)
	}
}

