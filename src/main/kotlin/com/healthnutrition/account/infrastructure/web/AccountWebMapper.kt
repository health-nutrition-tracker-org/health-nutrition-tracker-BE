package com.healthnutrition.account.infrastructure.web

import com.healthnutrition.account.infrastructure.web.dto.AccountRequest
import com.healthnutrition.account.infrastructure.web.dto.AccountResponse
import com.healthnutrition.account.usecase.dto.AccountDto

object AccountWebMapper {
	fun toSignInResponse(signInResult: AccountDto.SignInResult): AccountResponse.SignIn = AccountResponse.SignIn(
		accountId = signInResult.accountId,
		email = signInResult.email
	)

	fun toSignUpDto(signUpRequest: AccountRequest.SignUp): AccountDto.SignUp = AccountDto.SignUp(
		email = signUpRequest.email,
		password = signUpRequest.password
	)

	fun toSignInDto(signInRequest: AccountRequest.SignIn): AccountDto.SignIn = AccountDto.SignIn(
		email = signInRequest.email,
		password = signInRequest.password
	)
}
