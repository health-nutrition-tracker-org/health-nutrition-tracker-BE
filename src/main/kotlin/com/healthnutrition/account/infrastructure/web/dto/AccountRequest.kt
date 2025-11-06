package com.healthnutrition.account.infrastructure.web.dto

class AccountRequest {
	data class SignUp(
		val email: String,
		val password: String
	)

	data class SignIn(
		val email: String,
		val password: String
	)
}
