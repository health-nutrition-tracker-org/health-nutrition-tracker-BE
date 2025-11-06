package com.healthnutrition.account.domain.exception

import com.healthnutrition.global.domain.DomainException
import org.springframework.http.HttpStatus

open class AccountNotFoundException(
	override val status: HttpStatus = HttpStatus.NOT_FOUND,
	override var message: String
) : DomainException() {
	constructor(message: String) : this(
		status = HttpStatus.NOT_FOUND,
		message = message
	) {
		this.message = message
	}
}
