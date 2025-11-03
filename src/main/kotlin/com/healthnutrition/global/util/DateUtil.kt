package com.healthnutrition.global.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtil {
	/**
	 * Date 값을 날짜 패턴대로 출력
	 */
	fun formatDate(pattern: String, date: Date): String {
		val formatter = SimpleDateFormat(pattern, Locale.KOREA)
		return formatter.format(date)
	}
}