package com.healthnutrition.global.domain

import org.springframework.http.HttpStatus

abstract class DomainException : RuntimeException() {
	abstract val status: HttpStatus
	abstract override val message: String
	open var data: Any? = null
}
