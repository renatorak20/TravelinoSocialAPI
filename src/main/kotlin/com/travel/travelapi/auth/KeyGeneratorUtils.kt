package com.travel.travelapi.auth

import org.springframework.stereotype.Component
import java.security.KeyPair
import java.security.KeyPairGenerator

@Component
internal object KeyGeneratorUtils {
    fun generateRsaKey(): KeyPair {
        val keyPair: KeyPair
        try {
            val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
            keyPairGenerator.initialize(2048)
            keyPair = keyPairGenerator.generateKeyPair()
        } catch (ex: Exception) {
            throw IllegalStateException(ex)
        }
        return keyPair
    }
}