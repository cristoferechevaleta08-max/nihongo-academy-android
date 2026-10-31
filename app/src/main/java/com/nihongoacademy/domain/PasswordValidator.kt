package com.nihongoacademy.domain

data class PasswordStrength(
    val valid: Boolean,
    val score: Int,
    val message: String
)

class PasswordValidator {
    fun validate(password: String): PasswordStrength {
        val hasMinLength = password.length >= 8
        val hasLetter = password.any { it.isLetter() }
        val hasDigit = password.any { it.isDigit() }
        val score = listOf(hasMinLength, hasLetter, hasDigit).count { it } * 33

        return when {
            !hasMinLength || !hasLetter || !hasDigit -> PasswordStrength(
                valid = false,
                score = score.coerceAtMost(66),
                message = "Debe tener mínimo 8 caracteres, una letra y un número."
            )
            score >= 100 -> PasswordStrength(
                valid = true,
                score = 100,
                message = "Fuerte"
            )
            else -> PasswordStrength(
                valid = true,
                score = score,
                message = "Media"
            )
        }
    }
}
