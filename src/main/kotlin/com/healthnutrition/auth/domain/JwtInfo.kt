package com.healthnutrition.auth.domain

data class JwtInfo(
	val access: String,
	val refresh: String,
	val email: String,
	val accountId: Long,
	val accessExpiredAt: String
)
