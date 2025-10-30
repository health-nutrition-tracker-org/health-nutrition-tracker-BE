package com.healthnutrition.security.filter

import com.healthnutrition.security.jwt.JwtProvider
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
	private val jwtProvider: JwtProvider
) : OncePerRequestFilter() {
	override fun doFilterInternal(
		request: HttpServletRequest,
		response: HttpServletResponse,
		filterChain: FilterChain
	) {
		val authHeader = request.getHeader("Authorization")
		if (!authHeader.isNullOrBlank() && authHeader.startsWith("Bearer ")) {
			val bearerToken = authHeader.removePrefix("Bearer ").trim()
			try {
				val claim = jwtProvider.parseAccessToken(bearerToken)
				val authentication = UsernamePasswordAuthenticationToken(claim.accountId, null)

				request.setAttribute("accountId", claim.accountId)

				SecurityContextHolder.getContext().authentication = authentication
			} catch (e: Exception) {
				response.status = HttpServletResponse.SC_UNAUTHORIZED
				response.writer.write("""{"message":"Invalid or expired token"}""")
				return
			}
		}
		filterChain.doFilter(request, response)
	}
}