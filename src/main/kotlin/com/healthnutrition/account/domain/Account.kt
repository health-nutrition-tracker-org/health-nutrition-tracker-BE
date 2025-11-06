package com.healthnutrition.account.domain

import com.healthnutrition.account.domain.exception.AccountInternalException
import java.time.LocalDateTime

data class Account(
	val accountId: Long? = null,
	val email: String,
	val password: String,
	var lastSignInAt: LocalDateTime? = null,
	var createdAt: LocalDateTime? = null
) {
	fun verifySignUp() {
		if (email.isBlank() || password.isBlank()) {
			throw AccountInternalException("회원가입 에러: 이메일 또는 패스워드가 비어있습니다.")
		}

		val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
		if (!emailRegex.matches(email)) {
			throw AccountInternalException("회원가입 에러: 이메일 형식이 아닙니다.")
		}
	}

    fun verifyPassword(password: String) {
        if (password != this.password) {
            throw AccountInternalException("비밀번호가 올바르지 않습니다.")
        }
    }

	fun updateLastSignIn() {
		lastSignInAt = LocalDateTime.now()
	}
}
