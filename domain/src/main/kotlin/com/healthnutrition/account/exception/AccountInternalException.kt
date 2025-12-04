package com.healthnutrition.account.exception

import com.healthnutrition.DomainException

class AccountInternalException(
	message: String
) : DomainException(message = message, code = "ACCOUNT_INTERNAL_ERROR")
