package com.nihongoacademy.data

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Base64
import com.nihongoacademy.security.PasswordHash
import com.nihongoacademy.security.PasswordHasher
import java.util.UUID

data class AuthUser(
    val id: String,
    val displayName: String,
    val email: String
)

sealed interface AuthResult {
    data class Success(val user: AuthUser) : AuthResult
    data class Failure(val message: String) : AuthResult
}

class AuthRepository(context: Context) {
    private val appContext = context.applicationContext
    private val database = DatabaseProvider(appContext).database
    private val passwordHasher = PasswordHasher()
    private val sessionStore = SecureTokenStore(appContext)

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
        val userId = UUID.randomUUID().toString()
        val entity = UserEntity(
            userId = userId,
            visibleName = name,
            username = normalizedEmail,
            passwordHash = Base64.encodeToString(generated.hash, Base64.NO_WRAP),
            passwordSalt = Base64.encodeToString(generated.salt, Base64.NO_WRAP),
            iterations = generated.iterations,
            recoveryPhrase = "",
            createdAt = System.currentTimeMillis()
        )
        return try {
            database.userDao().insert(entity)
            sessionStore.saveToken(userId)
            AuthResult.Success(AuthUser(userId, name, normalizedEmail))
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
        sessionStore.saveToken(user.userId)
        return AuthResult.Success(AuthUser(user.userId, user.visibleName, user.username))
    }

    suspend fun currentUser(): AuthUser? {
        val userId = sessionStore.readToken() ?: return null
        val user = database.userDao().findById(userId) ?: return null
        return AuthUser(user.userId, user.visibleName, user.username)
    }

    fun logout() = sessionStore.clearToken()
}
