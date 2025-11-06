package com.healthnutrition.domain.account.infrastructure

import com.healthnutrition.domain.account.exception.AccountNotFoundException
import com.healthnutrition.domain.account.infrastructure.entity.Account
import com.healthnutrition.domain.account.service.repository.AccountRepository
import org.springframework.stereotype.Repository

@Repository
class AccountRepositoryImpl(
	private val accountRepository: AccountJpaRepository
) : AccountRepository {
	override fun save(entity: Account): Account =
		accountRepository.save(entity)

	override fun getByEmailOrThrow(email: String): Account {
		return accountRepository.findByEmail(email)
			?: throw AccountNotFoundException("이메일: $email 에 해당하는 계정은 존재하지 않습니다.")
	}
}
