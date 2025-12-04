package com.healthnutrition.encryption

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class ColumnEncryptConverter : AttributeConverter<String, String> {
	// 스프링 컨테이너 관리 범위 밖에서 Bean 꺼내 등록
	private val encryptionUseCase: EncryptionUseCase by lazy {
		ConvertSpringContext.Companion.getBean(EncryptionUseCase::class.java)
	}

	override fun convertToDatabaseColumn(attribute: String?): String? {
		return attribute?.let { encryptionUseCase.encrypt(it) }
	}

	override fun convertToEntityAttribute(dbData: String?): String? {
		return dbData?.let { encryptionUseCase.decrypt(it) }
	}
}