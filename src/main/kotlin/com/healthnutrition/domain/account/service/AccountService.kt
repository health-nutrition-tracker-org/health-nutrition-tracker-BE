package com.healthnutrition.domain.account.service

import com.healthnutrition.domain.account.dto.AccountRequest
import com.healthnutrition.domain.account.dto.AccountResponse
import com.healthnutrition.domain.account.infrastructure.AccountRepositoryService
import com.healthnutrition.domain.account.infrastructure.entity.Account
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountService(
	private val accountRepositoryService: AccountRepositoryService
) {
	@Transactional
	fun signUp(request: AccountRequest.SignUp): AccountResponse.SignIn {
		request.verify()

		val account = accountRepositoryService.save(
			Account.from(request)
		)

		return AccountResponse.SignIn.from(account)
	}
}
