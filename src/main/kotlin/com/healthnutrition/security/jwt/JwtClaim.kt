package com.healthnutrition.security.jwt

data class JwtClaim(
	val accountId: Long,
	val email: String
)
