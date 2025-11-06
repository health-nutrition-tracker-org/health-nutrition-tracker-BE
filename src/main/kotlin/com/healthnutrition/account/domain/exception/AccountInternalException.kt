package com.healthnutrition.account.domain.exception

import com.healthnutrition.global.domain.DomainException
import org.springframework.http.HttpStatus

open class AccountInternalException(
	override val status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
	override var message: String
) : DomainException() {
	constructor(message: String) : this(
		status = HttpStatus.INTERNAL_SERVER_ERROR,
		message = message
	) {
		this.message = message
	}
}
