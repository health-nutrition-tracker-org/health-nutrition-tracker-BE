package com.healthnutrition.account

import com.healthnutrition.account.exception.AccountInternalException

data class PasswordHash(val value: String) {
	fun verifyPassword(password: String) {
		if (password != this.value) {
			throw AccountInternalException("비밀번호가 올바르지 않습니다.")
		}
	}
}
