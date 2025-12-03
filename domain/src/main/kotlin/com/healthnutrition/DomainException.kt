package com.healthnutrition

open class DomainException(
	message: String,
	code: String,
	cause: Throwable? = null
) : RuntimeException(message, cause)
