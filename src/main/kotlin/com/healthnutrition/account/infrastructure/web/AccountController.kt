package com.healthnutrition.account.infrastructure.web

import com.healthnutrition.account.infrastructure.web.dto.AccountRequest
import com.healthnutrition.account.infrastructure.web.dto.AccountResponse
import com.healthnutrition.account.usecase.AccountUseCase
import com.healthnutrition.account.usecase.dto.AccountDto
import com.healthnutrition.auth.domain.JwtInfo
import com.healthnutrition.auth.usecase.JwtProvider
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController(
	private val accountUseCase: AccountUseCase,
	private val jwtProvider: JwtProvider
) {
	@PostMapping("v1/accounts")
	fun signUp(
		@RequestBody request: AccountRequest.SignUp
	): ResponseEntity<AccountResponse.SignIn> {
		return ResponseEntity.ok(
			AccountResponse.SignIn.from(
				accountUseCase.signUp(
					AccountDto.SignUp(
						email = request.email,
						password = request.password
					)
				)
			)
		)
	}

	@PostMapping("v1/accounts/tokens")
	fun signIn(
		@RequestBody request: AccountRequest.SignIn
	): ResponseEntity<JwtInfo> {
		val signInResponse = AccountResponse.SignIn.from(
			accountUseCase.signIn(
				AccountDto.SignIn(
					email = request.email,
					password = request.password
				)
			)
		)
		return ResponseEntity.ok(jwtProvider.issueToken(signInResponse))
	}
}