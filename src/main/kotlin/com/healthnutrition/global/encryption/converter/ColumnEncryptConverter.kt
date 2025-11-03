package com.healthnutrition.global.encryption.converter

import com.healthnutrition.global.config.SpringContext
import com.healthnutrition.global.encryption.service.EncryptionService
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class ColumnEncryptConverter : AttributeConverter<String, String> {
	// 스프링 컨테이너 관리 범위 밖에서 Bean 꺼내 등록
	private val encryptionService: EncryptionService by lazy {
		SpringContext.getBean(EncryptionService::class.java)
	}

	override fun convertToDatabaseColumn(attribute: String?): String? {
		return attribute?.let { encryptionService.encrypt(it) }
	}

	override fun convertToEntityAttribute(dbData: String?): String? {
		return dbData?.let { encryptionService.decrypt(it) }
	}
}
