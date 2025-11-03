package com.healthnutrition

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc

@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
@AutoConfigureRestDocs
abstract class MockMvcTest {
	@Autowired
	protected lateinit var mockMvc: MockMvc

	@Autowired
	protected open lateinit var objectMapper: ObjectMapper
}