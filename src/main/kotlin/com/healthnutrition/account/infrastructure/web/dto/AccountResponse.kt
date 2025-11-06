package com.healthnutrition.account.infrastructure.web.dto

import com.healthnutrition.account.usecase.dto.AccountDto

class AccountResponse {
	data class SignIn(
		val accountId: Long,
		val email: String
	) {
		companion object {
			fun from(dto: AccountDto.SignInResult): SignIn = SignIn(
				accountId = dto.accountId,
				email = dto.email
			)
		}
	}
}
