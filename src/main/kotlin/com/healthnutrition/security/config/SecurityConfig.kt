package com.healthnutrition.security.config

import com.healthnutrition.security.filter.JwtAuthFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableMethodSecurity
class SecurityConfig(
	private val jwtAuthFilter: JwtAuthFilter
) {
	@Bean
	fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
		return http
			.csrf { it.disable() }
			.sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
			.authorizeHttpRequests { auth ->
				auth
					.requestMatchers("/v1/accounts/**").permitAll()
					.anyRequest().authenticated()
			}
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
			.build()
	}
}