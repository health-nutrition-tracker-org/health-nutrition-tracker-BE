package com.healthnutrition.account

import com.healthnutrition.BaseEntity
import com.healthnutrition.encryption.ColumnEncryptConverter
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
) : BaseEntity() {
	fun updateLastSignInAt(lastSignInAt: LocalDateTime?) {
		this.lastSignInAt = lastSignInAt
	}
}