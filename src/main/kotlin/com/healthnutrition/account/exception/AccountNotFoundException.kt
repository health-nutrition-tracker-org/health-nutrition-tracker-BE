package com.healthnutrition.account.exception

import org.springframework.boot.logging.LogLevel
import org.springframework.http.HttpStatus

open class AccountNotFoundException(
	override var message: String?,
	val status: HttpStatus = HttpStatus.NOT_FOUND,
	val logLevel: LogLevel = LogLevel.ERROR,
	open var data: Any? = null
) : RuntimeException() {
	constructor(message: String) : this(message, HttpStatus.NOT_FOUND, LogLevel.ERROR) {
		this.message = message
	}
}
