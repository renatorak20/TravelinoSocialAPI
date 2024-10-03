package com.travel.travelapi.auth

@JvmRecord
data class LoginRequest(val username: String, val password: String)