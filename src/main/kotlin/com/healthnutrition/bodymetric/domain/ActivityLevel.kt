package com.healthnutrition.bodymetric.domain

import java.math.BigDecimal

enum class ActivityLevel(val description: String, val explanation: String, val factor: BigDecimal) {
	SEDENTARY(
		description = "비활동적",
		explanation = "주로 앉아 있거나 누워서 보내는 시간이 많고, 최소한의 움직임만 있는 수준입니다.",
		factor = BigDecimal.valueOf(1.2)
	),
	LIGHT(
		description = "저활동적",
		explanation = "일상적인 기본 활동 외에 약간의 가벼운 활동이 추가된 수준입니다.",
		factor = BigDecimal.valueOf(1.375)
	),
	MODERATE(
		description = "활동적",
		explanation = "심박수가 증가하고 숨이 약간 차는 정도의 신체 활동이 포함된 수준입니다.",
		factor = BigDecimal.valueOf(1.55)
	),
	ACTIVE(
		description = "매우 활동적",
		explanation = "심박수와 호흡이 상당히 증가하고 땀이 나는 정도의 활발한 신체 활동이 포함된 상태입니다.",
		factor = BigDecimal.valueOf(1.725)
	),
	VERY_ACTIVE(
		description = "초고활동적",
		explanation = "일상 활동 외에 강도 높은 신체 활동이나 격렬한 운동을 매일 장시간 하는 상태입니다.",
		factor = BigDecimal.valueOf(1.9)
	);
}