package com.healthnutrition.domain.account.dto

import com.healthnutrition.domain.account.infrastructure.entity.Account

class AccountResponse {
	data class SignIn(
		val accountId: Long,
		val email: String
	) {
		companion object {
			fun from(account: Account): SignIn =
				SignIn(
					accountId = account.id!!,
					email = account.email
				)
		}
	}
}
