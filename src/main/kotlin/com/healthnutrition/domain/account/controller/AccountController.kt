package com.healthnutrition.domain.account.controller

import com.healthnutrition.domain.account.dto.AccountRequest
import com.healthnutrition.domain.account.dto.AccountResponse
import com.healthnutrition.domain.account.service.AccountService
import com.healthnutrition.security.jwt.JwtInfo
import com.healthnutrition.security.jwt.JwtProvider
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController(
	private val accountService: AccountService,
	private val jwtProvider: JwtProvider
) {
	@PostMapping("v1/accounts")
	fun signUp(
		@RequestBody request: AccountRequest.SignUp
	): ResponseEntity<AccountResponse.SignIn> {
		return ResponseEntity.ok(accountService.signUp(request))
	}

	@PostMapping("v1/accounts/tokens")
	fun signIn(
		@RequestBody request: AccountRequest.SignIn
	): ResponseEntity<JwtInfo> {
		val signInResponse = accountService.signIn(request)
		return ResponseEntity.ok(jwtProvider.issueToken(signInResponse))
	}
}
