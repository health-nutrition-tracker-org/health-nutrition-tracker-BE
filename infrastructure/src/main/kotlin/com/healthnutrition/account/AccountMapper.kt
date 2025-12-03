package com.healthnutrition.account

object AccountMapper {
	fun toEntity(domain: Account): AccountEntity = AccountEntity(
		email = domain.email,
		password = domain.password,
		lastSignInAt = domain.lastSignInAt
	)

    fun toDomain(entity: AccountEntity): Account = Account(
        accountId = entity.id!!,
        email = entity.email,
        password = entity.password,
        lastSignInAt = entity.lastSignInAt,
        createdAt = entity.createdAt
    )
}