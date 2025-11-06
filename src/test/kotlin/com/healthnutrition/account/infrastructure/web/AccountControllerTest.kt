package com.healthnutrition.account.infrastructure.web

import com.healthnutrition.MockMvcTest
import com.healthnutrition.account.infrastructure.web.dto.AccountRequest
import com.healthnutrition.account.usecase.AccountUseCase
import com.healthnutrition.account.usecase.dto.AccountDto
import com.healthnutrition.auth.domain.JwtInfo
import com.healthnutrition.auth.infrastructure.config.SecurityConfig
import com.healthnutrition.auth.infrastructure.security.JwtAuthFilter
import com.healthnutrition.auth.usecase.JwtProvider
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(
	controllers = [AccountController::class],
	excludeFilters = [
		ComponentScan.Filter(
			type = FilterType.ASSIGNABLE_TYPE,
			classes = [SecurityConfig::class, JwtAuthFilter::class]
		)
	],
	excludeAutoConfiguration = [
		SecurityAutoConfiguration::class,
		SecurityFilterAutoConfiguration::class
	]
)
class AccountControllerTest : MockMvcTest() {
	@MockkBean
	private lateinit var accountUseCase: AccountUseCase

	@MockkBean
	private lateinit var jwtProvider: JwtProvider

	@Test
	@DisplayName("회원가입 테스트")
	fun signUp_mock_test() {
		val request = AccountDto.SignUp(
			email = "abc@example.com",
			password = "password1234"
		)
		val response = AccountDto.SignInResult(
			accountId = 1L,
			email = "abc@example.com"
		)

		every { accountUseCase.signUp(request) } returns response

		mockMvc.perform(
			RestDocumentationRequestBuilders.post("/v1/accounts")
				.content(objectMapper.writeValueAsString(request))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers.status().isOk)
			.andDo(
				MockMvcRestDocumentation.document(
					"Account-Sign-Up",
					Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
					Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
					PayloadDocumentation.requestFields(
						PayloadDocumentation.fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
						PayloadDocumentation.fieldWithPath("password").type(JsonFieldType.STRING).description("패스워드")
					),
					PayloadDocumentation.responseFields(
						PayloadDocumentation.fieldWithPath("accountId").type(JsonFieldType.NUMBER)
							.description("계정 식별자(ID)"),
						PayloadDocumentation.fieldWithPath("email").type(JsonFieldType.STRING).description("이메일")
					)
				)
			)
	}

	@Test
	@DisplayName("로그인 테스트")
	fun signIn_mock_test() {
		val request = AccountRequest.SignIn(
			email = "abc@example.com",
			password = "password1234"
		)
		val signInResponse = AccountDto.SignInResult(
			accountId = 1L,
			email = "abc@example.com"
		)
		val response = JwtInfo(
			access = "access-token",
			refresh = "refresh-token",
			email = "abc@example.com",
			accountId = 1L,
			accessExpiredAt = "2023-04-12 10:00:25"
		)

		every { accountUseCase.signIn(any()) } returns signInResponse
		every { jwtProvider.issueToken(any()) } returns response

		mockMvc.perform(
			RestDocumentationRequestBuilders.post("/v1/accounts/tokens")
				.content(objectMapper.writeValueAsString(request))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers.status().isOk)
			.andDo(
				MockMvcRestDocumentation.document(
					"Account-Sign-In",
					Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
					Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
					PayloadDocumentation.requestFields(
						PayloadDocumentation.fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
						PayloadDocumentation.fieldWithPath("password").type(JsonFieldType.STRING).description("패스워드")
					),
					PayloadDocumentation.responseFields(
						PayloadDocumentation.fieldWithPath("access").type(JsonFieldType.STRING).description("JWT access token"),
						PayloadDocumentation.fieldWithPath("refresh").type(JsonFieldType.STRING).description("JWT refresh token"),
						PayloadDocumentation.fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
						PayloadDocumentation.fieldWithPath("accountId").type(JsonFieldType.NUMBER).description("계정 식별자(ID)"),
						PayloadDocumentation.fieldWithPath("accessExpiredAt").type(JsonFieldType.STRING).description("토큰 만료일자")
					)
				)
			)
	}
}