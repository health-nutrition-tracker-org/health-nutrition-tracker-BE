package com.healthnutrition.global.usecase

import org.springframework.stereotype.Service
import java.security.Key
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

@Service
class EncryptionUseCase {
	private val encryptKey = "encryptkey1234"
	private val algorithm = "AES"

	fun encrypt(data: String): String {
		val cipher = Cipher.getInstance(algorithm)
		cipher.init(Cipher.ENCRYPT_MODE, generateKey(encryptKey))
		val encryptedBytes = cipher.doFinal(data.toByteArray())

		return Base64.getEncoder().encodeToString(encryptedBytes)
	}

	fun decrypt(encryptedData: String): String {
		val cipher = Cipher.getInstance(algorithm)
		cipher.init(Cipher.DECRYPT_MODE, generateKey(encryptKey))
		val decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData))

		return String(decryptedBytes)
	}

	private fun generateKey(encryptKey: String): Key {
		var key = encryptKey.toByteArray()
		val keyLengths = listOf(16, 24, 32) // 유효한 AES 키 길이
		val validLength = keyLengths.firstOrNull { encryptKey.length <= it } ?: keyLengths.last() // 최적의 키 길이 선택

		if (key.size < validLength) {
			key = key.copyOf(validLength) // 키 길이 조정
		}
		return SecretKeySpec(key, algorithm)
	}
}