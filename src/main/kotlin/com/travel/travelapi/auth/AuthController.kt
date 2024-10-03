package com.travel.travelapi.auth

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(tokenService: TokenService, private val authenticationManager: AuthenticationManager) {
    private val tokenService: TokenService = tokenService

    @PostMapping("/token")
    @Throws(AuthenticationException::class)
    fun token(@RequestBody userLogin: LoginRequest): String {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                userLogin.username,
                userLogin.password
            )
        )
        return tokenService.generateToken(authentication)
    }

    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(AuthController::class.java)
    }
}