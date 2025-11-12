package com.healthnutrition

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class HealthNutritionTrackerBeApplication

fun main(args: Array<String>) {
	runApplication<HealthNutritionTrackerBeApplication>(*args)
}
