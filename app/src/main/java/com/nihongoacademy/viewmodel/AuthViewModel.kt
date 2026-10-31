package com.nihongoacademy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nihongoacademy.domain.PasswordStrength
import com.nihongoacademy.domain.PasswordValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AuthUiState(
    val isLoggedIn: Boolean = false,
    val errorMessage: String? = null,
    val passwordStrength: PasswordStrength? = null,
    val isLoading: Boolean = false
)

class AuthViewModel : ViewModel() {
    private val validator = PasswordValidator()
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun validatePassword(password: String) {
        val strength = validator.validate(password)
        _uiState.value = _uiState.value.copy(passwordStrength = strength)
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            val ok = username.isNotBlank() && password.length >= 8
            _uiState.value = _uiState.value.copy(
                isLoggedIn = ok,
                isLoading = false,
                errorMessage = if (ok) null else "Credenciales inválidas"
            )
        }
    }
}
