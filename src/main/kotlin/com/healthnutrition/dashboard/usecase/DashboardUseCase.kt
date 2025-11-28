package com.healthnutrition.dashboard.usecase

import com.healthnutrition.bodymetric.domain.BodyMetricRepository
import com.healthnutrition.bodymetric.infrastructure.persistence.BodyMetricMapper
import com.healthnutrition.dashboard.usecase.dto.DashboardDto
import com.healthnutrition.food.domain.FoodLogRepository
import com.healthnutrition.food.infrastructure.persistence.FoodMapper
import com.healthnutrition.global.util.DateUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalTime
import java.time.ZoneId

@Service
class DashboardUseCase(
	private val bodyMetricRepository: BodyMetricRepository,
	private val foodLogRepository: FoodLogRepository
) {
    /**
     * 사용자 별 오늘 섭취한 칼로리 vs 일일 권장 섭취 칼로리
     */
    @Transactional(readOnly = true)
    fun getKcalDiffInfo(accountId: Long, date: String): DashboardDto.KcalDiffInfo {
	    val targetDate = DateUtil.parseDate("yyyy-MM-dd", date)
		val startDate = targetDate.atStartOfDay(ZoneId.of("Asia/Seoul")).toLocalDateTime() // 오늘 하루의 시작 일자
	    val endDate = targetDate.atTime(LocalTime.MAX) // 오늘 하루의 끝 일자

	    val tdee = BodyMetricMapper.toDomain(
			entity = bodyMetricRepository.getByAccountIdOrThrow(accountId)
		).calculateTdee()
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
}

