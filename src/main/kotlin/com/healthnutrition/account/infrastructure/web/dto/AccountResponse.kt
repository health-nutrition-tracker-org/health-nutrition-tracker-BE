package com.healthnutrition.account.infrastructure.web.dto

class AccountResponse {
	data class SignIn(
		val accountId: Long,
		val email: String
	)
}
