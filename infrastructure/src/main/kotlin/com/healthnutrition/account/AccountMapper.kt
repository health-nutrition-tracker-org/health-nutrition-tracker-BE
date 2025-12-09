package com.healthnutrition.account

object AccountMapper {
	fun toEntity(domain: Account): AccountEntity = AccountEntity(
		email = domain.email.value,
		password = domain.password.value,
		lastSignInAt = domain.lastSignInAt
	)

    fun toDomain(entity: AccountEntity): Account = Account(
        accountId = entity.id!!,
        email = Email(value = entity.email),
        password = PasswordHash(value = entity.password),
        lastSignInAt = entity.lastSignInAt,
        createdAt = entity.createdAt
    )
}