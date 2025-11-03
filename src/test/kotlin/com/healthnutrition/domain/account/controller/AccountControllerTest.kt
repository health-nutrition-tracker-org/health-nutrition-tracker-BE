package com.healthnutrition.domain.account.controller

import com.healthnutrition.MockMvcTest
import com.healthnutrition.domain.account.dto.AccountRequest
import com.healthnutrition.domain.account.dto.AccountResponse
import com.healthnutrition.domain.account.service.AccountService
import com.healthnutrition.security.config.SecurityConfig
import com.healthnutrition.security.filter.JwtAuthFilter
import com.healthnutrition.security.jwt.JwtInfo
import com.healthnutrition.security.jwt.JwtProvider
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest
import org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse
import org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
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
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration::class,
        org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration::class
    ]
)
class AccountControllerTest : MockMvcTest() {
    @MockkBean
    private lateinit var accountService: AccountService

    @MockkBean
    private lateinit var jwtProvider: JwtProvider

    @Test
    @DisplayName("회원가입 테스트")
    fun signUp_mock_test() {
        val request = AccountRequest.SignUp(
            email = "abc@example.com",
            password = "password1234"
        )
        val response = AccountResponse.SignIn(
            accountId = 1L,
            email = "abc@example.com"
        )

        every { accountService.signUp(request) } returns response

        mockMvc.perform(
            RestDocumentationRequestBuilders.post("/v1/accounts")
                .content(objectMapper.writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(
                MockMvcRestDocumentation.document(
                    "Account-Sign-Up",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    PayloadDocumentation.requestFields(
                        PayloadDocumentation.fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                        PayloadDocumentation.fieldWithPath("password").type(JsonFieldType.STRING).description("패스워드")
                    ),
                    PayloadDocumentation.responseFields(
                        PayloadDocumentation.fieldWithPath("accountId").type(JsonFieldType.NUMBER).description("계정 식별자(ID)"),
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
        val signInResponse = AccountResponse.SignIn(
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

        every { accountService.signIn(any()) } returns signInResponse
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
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
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