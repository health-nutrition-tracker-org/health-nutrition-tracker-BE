package com.healthnutrition.account.infrastructure.persistence

import com.healthnutrition.account.domain.AccountRepository
import com.healthnutrition.account.domain.exception.AccountNotFoundException
import org.springframework.stereotype.Repository

@Repository
class AccountRepositoryImpl(
	private val accountRepository: AccountJpaRepository
) : AccountRepository {
	override fun save(entity: AccountEntity): AccountEntity =
		accountRepository.save(entity)

	override fun getByEmailOrThrow(email: String): AccountEntity {
		return accountRepository.findByEmail(email)
			?: throw AccountNotFoundException("이메일: $email 에 해당하는 계정은 존재하지 않습니다.")
	}
}