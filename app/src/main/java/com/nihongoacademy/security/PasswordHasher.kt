package com.nihongoacademy.security

import java.security.SecureRandom
import java.security.spec.KeySpec
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

data class PasswordHash(
    val salt: ByteArray,
    val hash: ByteArray,
    val iterations: Int = 310_000
)

class PasswordHasher {
    private val secureRandom = SecureRandom()

    fun hashPassword(password: String): PasswordHash {
        val salt = ByteArray(16)
        secureRandom.nextBytes(salt)
        val spec: KeySpec = PBEKeySpec(password.toCharArray(), salt, 310_000, 256)
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val hash = factory.generateSecret(spec).encoded
        return PasswordHash(salt, hash, 310_000)
    }

    fun verifyPassword(password: String, stored: PasswordHash): Boolean {
        val spec: KeySpec = PBEKeySpec(password.toCharArray(), stored.salt, stored.iterations, 256)
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val computed = factory.generateSecret(spec).encoded
        return computed.contentEquals(stored.hash)
    }
}
