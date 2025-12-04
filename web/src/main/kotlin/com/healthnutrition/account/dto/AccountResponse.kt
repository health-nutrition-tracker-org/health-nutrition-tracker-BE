package com.healthnutrition.account.dto

class AccountResponse {
	data class SignIn(
		val accountId: Long,
		val email: String
	)
}
