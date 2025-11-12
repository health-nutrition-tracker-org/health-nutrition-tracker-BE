package com.healthnutrition.account.usecase

import com.healthnutrition.account.domain.Account
import com.healthnutrition.account.domain.AccountRepository
import com.healthnutrition.account.infrastructure.persistence.AccountMapper
import com.healthnutrition.account.usecase.dto.AccountDto
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

		val accountEntity = accountRepositoryService.save(
			AccountMapper.toEntity(account)
		)

		return AccountDto.SignInResult(
			accountId = accountEntity.id!!,
			email = accountEntity.email
		)
	}

	@Transactional
	fun signIn(request: AccountDto.SignIn): AccountDto.SignInResult {
		val accountEntity = accountRepositoryService.getByEmailOrThrow(request.email)
		val account = AccountMapper.toDomain(
			entity = accountEntity
		)
		account.verifyPassword(request.password)
		account.updateLastSignIn()
		accountEntity.updateLastSignInAt(account.lastSignInAt)

		return AccountDto.SignInResult(
			accountId = account.accountId!!,
			email = account.email
		)
	}
}
