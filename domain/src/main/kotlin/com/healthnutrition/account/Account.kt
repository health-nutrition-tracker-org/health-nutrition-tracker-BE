package com.healthnutrition.account

import java.time.LocalDateTime

data class Account(
	val accountId: Long? = null,
	val email: Email,
	val password: PasswordHash,
	var lastSignInAt: LocalDateTime? = null,
	var createdAt: LocalDateTime? = null
) {
    fun verifyPassword(password: String) {
		this.password.verifyPassword(password)
    }

	fun updateLastSignIn() {
		lastSignInAt = LocalDateTime.now()
	}
}