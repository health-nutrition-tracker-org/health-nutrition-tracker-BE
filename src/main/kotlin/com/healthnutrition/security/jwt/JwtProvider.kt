package com.healthnutrition.security.jwt

import com.healthnutrition.account.infrastructure.web.dto.AccountResponse
import com.healthnutrition.global.util.DateUtil
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.Date

@Component
class JwtProvider(
	@Value("\${jwt.secret-key}") private val secretKey: String,
	@Value("\${jwt.access-ttl-ms}") private val accessTtlMs: Long,
	@Value("\${jwt.refresh-ttl-ms}") private val refreshTtlMs: Long
) {
	private val key by lazy { Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8)) }

	/**
	 * JWT 발급
	 */
	fun issueToken(accountResponse: AccountResponse.SignIn): JwtInfo {
		val now = Date()
		val accessExp = Date(now.time + accessTtlMs)
		val refreshExp = Date(now.time + refreshTtlMs)

		val baseClaims = mapOf(
			"email" to accountResponse.email
		)

		val access = Jwts.builder()
			.setSubject(accountResponse.accountId.toString())
			.addClaims(baseClaims)
			.setIssuedAt(now)
			.setExpiration(accessExp)
			.signWith(key, SignatureAlgorithm.HS256)
			.compact()

		val refresh = Jwts.builder()
			.setSubject(accountResponse.accountId.toString())
			.claim("typ", "refresh")
			.setIssuedAt(now)
			.setExpiration(refreshExp)
			.signWith(key, SignatureAlgorithm.HS256)
			.compact()

		return JwtInfo(
			access = access,
			refresh = refresh,
			email = accountResponse.email,
			accountId = accountResponse.accountId,
			accessExpiredAt = DateUtil.formatDate("yyyy-MM-dd HH:mm:ss", accessExp)
		)
	}

	/**
	 * JWT 검증 및 파싱
	 */
	fun parseAccessToken(token: String): JwtClaim {
		val claimBody = Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token).body

		val accountId = claimBody.subject.toLong()
		val email = claimBody["email"].toString()

		return JwtClaim(
			accountId = accountId,
			email = email
		)
	}
}
