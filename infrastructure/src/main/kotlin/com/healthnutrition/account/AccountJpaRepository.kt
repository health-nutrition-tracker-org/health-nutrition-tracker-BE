package com.healthnutrition.account

import org.springframework.data.jpa.repository.JpaRepository

interface AccountJpaRepository : JpaRepository<AccountEntity, Long> {
	fun findByEmail(email: String): AccountEntity?
}