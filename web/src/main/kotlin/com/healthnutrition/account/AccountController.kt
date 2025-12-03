package com.healthnutrition.account

import com.healthnutrition.account.dto.AccountRequest
import com.healthnutrition.account.dto.AccountResponse
import com.healthnutrition.jwt.JwtProviderUseCase
import com.healthnutrition.jwt.dto.JwtInfo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController(
	private val accountUseCase: AccountUseCase,
	private val jwtProvider: JwtProviderUseCase
) {
	@PostMapping("v1/accounts")
	fun signUp(
		@RequestBody request: AccountRequest.SignUp
	): ResponseEntity<AccountResponse.SignIn> {
		return ResponseEntity.ok(
			AccountWebMapper.toSignInResponse(
				accountUseCase.signUp(
					AccountWebMapper.toSignUpDto(request)
				)
			)
		)
	}

	@PostMapping("v1/accounts/tokens")
	fun signIn(
		@RequestBody request: AccountRequest.SignIn
	): ResponseEntity<JwtInfo> {
		val signInResponse = AccountWebMapper.toSignInResponse(
			accountUseCase.signIn(
				AccountWebMapper.toSignInDto(request)
			)
		)
		return ResponseEntity.ok(
			jwtProvider.issueToken(
				accountId = signInResponse.accountId,
				email = signInResponse.email
			)
		)
	}
}