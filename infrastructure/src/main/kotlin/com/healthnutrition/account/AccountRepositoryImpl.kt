package com.healthnutrition.account

import com.healthnutrition.account.exception.AccountNotFoundException
import org.springframework.stereotype.Repository

@Repository
class AccountRepositoryImpl(
	private val accountRepository: AccountJpaRepository
) : AccountRepository {
	override fun save(account: Account): Account {
		val savedEntity = accountRepository.save(AccountMapper.toEntity(account))
		return AccountMapper.toDomain(savedEntity)
	}


	override fun getByEmailOrThrow(email: String): Account {
		return accountRepository.findByEmail(email)?.let { AccountMapper.toDomain(it) }
			?: throw AccountNotFoundException("이메일: $email 에 해당하는 계정은 존재하지 않습니다.")
	}
}