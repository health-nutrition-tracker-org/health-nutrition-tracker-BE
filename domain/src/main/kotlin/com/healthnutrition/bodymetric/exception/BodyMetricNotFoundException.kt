package com.healthnutrition.bodymetric.exception

import com.healthnutrition.DomainException

open class BodyMetricNotFoundException(
	message: String
) : DomainException(message = message, code = "BODY_METRIC_NOT_FOUND")