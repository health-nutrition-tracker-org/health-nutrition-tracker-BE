package com.healthnutrition.domain.account.service.repository

import com.healthnutrition.domain.account.infrastructure.entity.Account

interface AccountRepository {
    fun save(entity: Account): Account

    fun getByEmailOrThrow(email: String): Account
}
