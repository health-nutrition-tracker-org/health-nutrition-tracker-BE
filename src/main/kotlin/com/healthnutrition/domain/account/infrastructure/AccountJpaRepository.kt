package com.healthnutrition.domain.account.infrastructure

import com.healthnutrition.domain.account.infrastructure.entity.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountJpaRepository : JpaRepository<Account, Long> {
	fun findByEmail(email: String): Account?
}
