package com.healthnutrition.account.domain

import com.healthnutrition.account.infrastructure.persistence.AccountEntity

interface AccountRepository {
	fun save(entity: AccountEntity): AccountEntity

	fun getByEmailOrThrow(email: String): AccountEntity
}