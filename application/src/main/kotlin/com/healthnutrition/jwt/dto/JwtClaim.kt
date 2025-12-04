package com.healthnutrition.jwt.dto

data class JwtClaim(
	val accountId: Long,
	val email: String,
	val role: String
)
