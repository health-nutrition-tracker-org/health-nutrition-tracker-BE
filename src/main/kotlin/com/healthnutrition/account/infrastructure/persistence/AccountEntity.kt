package com.healthnutrition.account.infrastructure.persistence

import com.healthnutrition.global.infrastructure.persistence.BaseEntity
import com.healthnutrition.global.infrastructure.persistence.ColumnEncryptConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "account")
class AccountEntity(
	@Column
	val email: String,

	@Column
	@Convert(converter = ColumnEncryptConverter::class)
	val password: String,

	@Column(name = "last_sign_in_at")
	var lastSignInAt: LocalDateTime? = null
) : BaseEntity()