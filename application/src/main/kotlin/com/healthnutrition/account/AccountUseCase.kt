package com.healthnutrition.account

import com.healthnutrition.account.dto.AccountDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountUseCase(
	private val accountRepositoryService: AccountRepository
) {
	@Transactional
	fun signUp(request: AccountDto.SignUp): AccountDto.SignInResult {
		val account = Account(email = request.email, password = request.password)
		account.verifySignUp()
		account.updateLastSignIn()

		val savedAccount = accountRepositoryService.save(account)

		return AccountDto.SignInResult(
			accountId = savedAccount.accountId!!,
			email = savedAccount.email
		)
	}

	@Transactional
	fun signIn(request: AccountDto.SignIn): AccountDto.SignInResult {
		val account = accountRepositoryService.getByEmailOrThrow(request.email)
		account.verifyPassword(request.password)
		account.updateLastSignIn()
		accountRepositoryService.updateLastSignInAtNow(account.email)

		return AccountDto.SignInResult(
			accountId = account.accountId!!,
			email = account.email
		)
	}
}