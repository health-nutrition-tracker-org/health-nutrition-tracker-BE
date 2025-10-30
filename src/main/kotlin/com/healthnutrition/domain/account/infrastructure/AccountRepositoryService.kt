package com.healthnutrition.domain.account.infrastructure

import com.healthnutrition.domain.account.infrastructure.entity.Account
import org.springframework.stereotype.Repository

@Repository
class AccountRepositoryService(
	private val accountRepository: AccountJpaRepository
) {
	fun save(entity: Account): Account =
		accountRepository.save(entity)
}
