package com.healthnutrition.bodymetric

interface BodyMetricRepository {
	fun save(bodyMetric: BodyMetric): BodyMetric

	fun getByAccountIdOrThrow(accountId: Long): BodyMetric
}