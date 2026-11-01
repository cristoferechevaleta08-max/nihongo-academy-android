package com.nihongoacademy.data

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Base64
import com.nihongoacademy.security.PasswordHash
import com.nihongoacademy.security.PasswordHasher
import java.util.UUID
import java.security.MessageDigest
import java.security.SecureRandom

data class AuthUser(
    val id: String,
    val displayName: String,
    val email: String
)

sealed interface AuthResult {
    data class Success(val user: AuthUser, val recoveryCode: String? = null) : AuthResult
    data class Failure(val message: String) : AuthResult
}

class AuthRepository(context: Context) {
    private val appContext = context.applicationContext
    private val database = DatabaseProvider(appContext).database
    private val passwordHasher = PasswordHasher()
    private val sessionStore = SessionStore(appContext)

    suspend fun register(displayName: String, email: String, password: String): AuthResult {
        val name = displayName.trim()
        val normalizedEmail = email.trim().lowercase()
        if (name.length < 2) return AuthResult.Failure("Escribe un nombre de al menos 2 caracteres.")
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(normalizedEmail).matches()) {
            return AuthResult.Failure("Introduce un correo electrónico válido.")
        }
        if (password.length < 8) return AuthResult.Failure("La contraseña debe tener al menos 8 caracteres.")
        if (database.userDao().findByUsername(normalizedEmail) != null) {
            return AuthResult.Failure("Ya existe una cuenta con ese correo.")
        }

        val generated = passwordHasher.hashPassword(password)
        val recoveryCode = generateRecoveryCode()
        val userId = UUID.randomUUID().toString()
        val entity = UserEntity(
            userId = userId,
            visibleName = name,
            username = normalizedEmail,
            passwordHash = Base64.encodeToString(generated.hash, Base64.NO_WRAP),
            passwordSalt = Base64.encodeToString(generated.salt, Base64.NO_WRAP),
            iterations = generated.iterations,
            recoveryPhrase = hashRecoveryCode(recoveryCode),
            createdAt = System.currentTimeMillis()
        )
        return try {
            database.userDao().insert(entity)
            sessionStore.saveUser(userId)
            AuthResult.Success(AuthUser(userId, name, normalizedEmail), recoveryCode)
        } catch (_: SQLiteConstraintException) {
            AuthResult.Failure("Ya existe una cuenta con ese correo.")
        } catch (_: Exception) {
            AuthResult.Failure("No se pudo crear la cuenta. Inténtalo nuevamente.")
        }
    }

    suspend fun login(email: String, password: String): AuthResult {
        val normalizedEmail = email.trim().lowercase()
        if (normalizedEmail.isBlank() || password.isBlank()) {
            return AuthResult.Failure("Completa el correo y la contraseña.")
        }
        val user = database.userDao().findByUsername(normalizedEmail)
            ?: return AuthResult.Failure("Correo o contraseña incorrectos.")
        val stored = runCatching {
            PasswordHash(
                salt = Base64.decode(user.passwordSalt, Base64.NO_WRAP),
                hash = Base64.decode(user.passwordHash, Base64.NO_WRAP),
                iterations = user.iterations
            )
        }.getOrElse { return AuthResult.Failure("No se pudieron verificar las credenciales.") }

        if (!passwordHasher.verifyPassword(password, stored)) {
            return AuthResult.Failure("Correo o contraseña incorrectos.")
        }
        sessionStore.saveUser(user.userId)
        return AuthResult.Success(AuthUser(user.userId, user.visibleName, user.username))
    }


    suspend fun resetPassword(email: String, recoveryCode: String, newPassword: String): AuthResult {
        val normalizedEmail = email.trim().lowercase()
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(normalizedEmail).matches()) {
            return AuthResult.Failure("Introduce el correo asociado a tu cuenta.")
        }
        if (recoveryCode.isBlank()) return AuthResult.Failure("Introduce tu código de recuperación.")
        if (newPassword.length < 8) return AuthResult.Failure("La nueva contraseña debe tener al menos 8 caracteres.")

        val user = database.userDao().findByUsername(normalizedEmail)
            ?: return AuthResult.Failure("El correo o el código de recuperación no son válidos.")
        val expected = user.recoveryPhrase.toByteArray(Charsets.UTF_8)
        val supplied = hashRecoveryCode(recoveryCode).toByteArray(Charsets.UTF_8)
        if (expected.isEmpty() || !MessageDigest.isEqual(expected, supplied)) {
            return AuthResult.Failure("El correo o el código de recuperación no son válidos.")
        }

        return runCatching {
            val generated = passwordHasher.hashPassword(newPassword)
            val newRecoveryCode = generateRecoveryCode()
            database.userDao().updatePassword(
                userId = user.userId,
                passwordHash = Base64.encodeToString(generated.hash, Base64.NO_WRAP),
                passwordSalt = Base64.encodeToString(generated.salt, Base64.NO_WRAP),
                iterations = generated.iterations,
                recoveryPhrase = hashRecoveryCode(newRecoveryCode)
            )
            sessionStore.clearUser()
            AuthResult.Success(AuthUser(user.userId, user.visibleName, user.username), newRecoveryCode)
        }.getOrElse {
            AuthResult.Failure("No se pudo actualizar la contraseña. Inténtalo nuevamente.")
        }
    }

    private fun generateRecoveryCode(): String {
        val alphabet = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789"
        val random = SecureRandom()
        val raw = (1..12).map { alphabet[random.nextInt(alphabet.length)] }.joinToString("")
        return raw.chunked(4).joinToString("-")
    }

    private fun hashRecoveryCode(code: String): String {
        val normalized = code.filterNot { it == '-' || it.isWhitespace() }.uppercase()
        val digest = MessageDigest.getInstance("SHA-256").digest(normalized.toByteArray(Charsets.UTF_8))
        return digest.joinToString("") { byte -> "%02x".format(byte) }
    }

    suspend fun currentUser(): AuthUser? {
        val userId = sessionStore.readUser() ?: return null
        val user = database.userDao().findById(userId) ?: return null
        return AuthUser(user.userId, user.visibleName, user.username)
    }

    suspend fun logout() = sessionStore.clearUser()
}
