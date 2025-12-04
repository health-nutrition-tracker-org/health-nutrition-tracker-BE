package com.healthnutrition.shared.util

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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

	/**
	 * LocalDateTime 값을 날짜 패턴대로 출력
	 */
	fun formatLocalDateTime(pattern: String, dateTime: LocalDateTime): String {
		val formatter = DateTimeFormatter.ofPattern(pattern, Locale.KOREA)
		return dateTime.format(formatter)
	}

	/**
	 * 날짜 문자열 값을 LocalDate로 포맷팅
	 */
	fun parseDate(pattern: String, dateStr: String): LocalDate =
		LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern))
}