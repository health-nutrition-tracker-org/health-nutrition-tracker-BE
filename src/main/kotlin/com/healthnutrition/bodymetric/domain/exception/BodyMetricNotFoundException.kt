package com.healthnutrition.bodymetric.domain.exception

import com.healthnutrition.global.domain.DomainException
import org.springframework.http.HttpStatus

open class BodyMetricNotFoundException(
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