package com.healthnutrition.account

interface AccountRepository {
	fun save(account: Account): Account

	fun getByEmailOrThrow(email: String): Account

	fun updateLastSignInAtNow(email: String)
}