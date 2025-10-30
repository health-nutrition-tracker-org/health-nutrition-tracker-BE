package com.healthnutrition.domain.account.controller

import com.healthnutrition.domain.account.dto.AccountRequest
import com.healthnutrition.domain.account.dto.AccountResponse
import com.healthnutrition.domain.account.service.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController(
	private val accountService: AccountService
) {
	@PostMapping("v1/accounts")
	fun signUp(
		@RequestBody request: AccountRequest.SignUp
	): ResponseEntity<AccountResponse.SignIn> {
		return ResponseEntity.ok(accountService.signUp(request))
	}
}
