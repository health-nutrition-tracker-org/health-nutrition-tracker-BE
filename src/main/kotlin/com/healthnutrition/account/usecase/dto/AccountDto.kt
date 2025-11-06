package com.healthnutrition.account.usecase.dto

class AccountDto {
	data class SignUp(
		val email: String,
		val password: String
	)

	data class SignIn(
		val email: String,
		val password: String
	)

    data class SignInResult(
        val accountId: Long,
        val email: String
    )
}
