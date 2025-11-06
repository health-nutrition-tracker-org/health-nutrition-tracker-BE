package com.healthnutrition.domain.account.service

import com.healthnutrition.domain.account.dto.AccountRequest
import com.healthnutrition.domain.account.dto.AccountResponse
import com.healthnutrition.domain.account.infrastructure.entity.Account
import com.healthnutrition.domain.account.service.repository.AccountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountService(
	private val accountRepositoryService: AccountRepository
) {
	@Transactional
	fun signUp(request: AccountRequest.SignUp): AccountResponse.SignIn {
		request.verify()

		val account = accountRepositoryService.save(
			Account.from(request)
		)

		return AccountResponse.SignIn.from(account)
	}

	@Transactional
	fun signIn(request: AccountRequest.SignIn): AccountResponse.SignIn {
		val account = accountRepositoryService.getByEmailOrThrow(request.email)
		account.verifyPassword(request.password)
		account.updateLastSignIn()

		return AccountResponse.SignIn.from(account)
	}
}
