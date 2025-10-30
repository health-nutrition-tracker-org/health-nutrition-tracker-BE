package com.healthnutrition.global.config

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

/**
 * JPA 2.1 버전 이하에서 컬럼 Convert 기능 사용을 위한 Bean 선택용 컴포넌트
 */
@Component
class SpringContext : ApplicationContextAware {

	companion object {
		private lateinit var context: ApplicationContext

		fun getBean(beanName: String): Any {
			return context.getBean(beanName)
		}

		fun <T> getBean(clazz: Class<T>): T {
			return context.getBean(clazz)
		}
	}

	override fun setApplicationContext(applicationContext: ApplicationContext) {
		context = applicationContext
	}
}
