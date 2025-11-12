package com.healthnutrition.food.infrastructure.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface FoodLogJpaRepository : JpaRepository<FoodLogEntity, Long> {
}
