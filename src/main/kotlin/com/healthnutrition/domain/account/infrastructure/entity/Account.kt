package com.healthnutrition.domain.account.infrastructure.entity

import com.healthnutrition.domain.account.dto.AccountRequest
import com.healthnutrition.global.domain.BaseEntity
import com.healthnutrition.global.encryption.converter.ColumnEncryptConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "account")
class Account(
	@Column
	val email: String,

	@Column
	@Convert(converter = ColumnEncryptConverter::class)
	val password: String,

	@Column("last_sign_in_at")
	var lastSignInAt: LocalDateTime? = null
) : BaseEntity() {
    companion object {
        fun from(signUpRequest: AccountRequest.SignUp): Account =
            Account(
                email = signUpRequest.email,
                password = signUpRequest.password,
                lastSignInAt = LocalDateTime.now()
            )
    }

	fun updateLastSignIn() {
		lastSignInAt = LocalDateTime.now()
	}
}
