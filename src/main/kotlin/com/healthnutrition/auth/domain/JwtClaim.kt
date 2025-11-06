package com.healthnutrition.auth.domain

data class JwtClaim(
	val accountId: Long,
	val email: String
)
