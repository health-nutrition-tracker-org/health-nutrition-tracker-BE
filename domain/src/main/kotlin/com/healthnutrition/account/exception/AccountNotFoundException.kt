package com.healthnutrition.account.exception

import com.healthnutrition.DomainException

open class AccountNotFoundException(
	message: String
) : DomainException(message = message, code = "ACCOUNT_NOT_FOUND")
