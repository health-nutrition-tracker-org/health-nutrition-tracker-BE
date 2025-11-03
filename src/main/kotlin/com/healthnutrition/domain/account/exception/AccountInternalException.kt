package com.healthnutrition.domain.account.exception

import org.springframework.boot.logging.LogLevel
import org.springframework.http.HttpStatus

open class AccountInternalException(
	override var message: String?,
	val status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
	val logLevel: LogLevel = LogLevel.ERROR,
	open var data: Any? = null
) : RuntimeException() {
	constructor(message: String) : this(message, HttpStatus.INTERNAL_SERVER_ERROR, LogLevel.ERROR) {
		this.message = message
	}
}
