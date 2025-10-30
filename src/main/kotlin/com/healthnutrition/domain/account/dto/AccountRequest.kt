package com.healthnutrition.domain.account.dto

import com.healthnutrition.domain.account.exception.AccountInternalException

class AccountRequest {
	data class SignUp(
		val email: String,
		val password: String
	) {
		fun verify() {
			if (email.isBlank() || password.isBlank()) {
				throw AccountInternalException("회원가입 에러: 이메일 또는 패스워드가 비어있습니다.")
			}

			val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
			if (!emailRegex.matches(email)) {
				throw AccountInternalException("회원가입 에러: 이메일 형식이 아닙니다.")
			}
		}
	}
}
