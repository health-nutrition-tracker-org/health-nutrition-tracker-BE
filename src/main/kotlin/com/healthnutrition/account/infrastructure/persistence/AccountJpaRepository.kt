package com.healthnutrition.account.infrastructure.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface AccountJpaRepository : JpaRepository<AccountEntity, Long> {
	fun findByEmail(email: String): AccountEntity?
}