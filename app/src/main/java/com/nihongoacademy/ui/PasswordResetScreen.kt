package com.nihongoacademy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nihongoacademy.data.AuthRepository
import com.nihongoacademy.data.AuthResult
import kotlinx.coroutines.launch

@Composable
fun PasswordResetScreen(navController: NavController) {
    val context = LocalContext.current
    val clipboard = LocalClipboardManager.current
    val repository = remember { AuthRepository(context) }
    val scope = rememberCoroutineScope()
    var email by remember { mutableStateOf("") }
    var recoveryCode by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var message by remember { mutableStateOf<String?>(null) }
    var completed by remember { mutableStateOf(false) }
    var renewedRecoveryCode by remember { mutableStateOf<String?>(null) }
    var loading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Restablecer contraseña", style = MaterialTheme.typography.headlineMedium)
        Text(
            "Introduce el correo de tu cuenta y el código de recuperación que guardaste al registrarte.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 8.dp, bottom = 18.dp)
        )
        if (!completed) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it; message = null },
                label = { Text("Correo electrónico") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = recoveryCode,
                onValueChange = { recoveryCode = it; message = null },
                label = { Text("Código de recuperación") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
            )
            OutlinedTextField(
                value = newPassword,
                onValueChange = { newPassword = it; message = null },
                label = { Text("Nueva contraseña (mínimo 8 caracteres)") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
            )
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it; message = null },
                label = { Text("Repetir nueva contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
            )
            message?.let { Text(it, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 10.dp)) }
            Button(
                onClick = {
                    if (newPassword != confirmPassword) {
                        message = "Las contraseñas no coinciden."
                    } else {
                        loading = true
                        message = null
                        scope.launch {
                            when (val result = repository.resetPassword(email, recoveryCode, newPassword)) {
                                is AuthResult.Success -> {
                                    renewedRecoveryCode = result.recoveryCode
                                    completed = true
                                }
                                is AuthResult.Failure -> message = result.message
                            }
                            loading = false
                        }
                    }
                },
                enabled = !loading && email.isNotBlank() && recoveryCode.isNotBlank() && newPassword.isNotBlank() && confirmPassword.isNotBlank(),
                modifier = Modifier.fillMaxWidth().padding(top = 18.dp)
            ) { Text(if (loading) "Actualizando..." else "Actualizar contraseña") }
        } else {
            Text("Contraseña actualizada correctamente.", color = MaterialTheme.colorScheme.primary)
            Text("Guarda tu nuevo código de recuperación. El anterior ya no sirve.", modifier = Modifier.padding(top = 10.dp))
            Card(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                Text(renewedRecoveryCode.orEmpty(), style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(18.dp))
            }
            Button(
                onClick = { clipboard.setText(AnnotatedString(renewedRecoveryCode.orEmpty())) },
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
            ) { Text("Copiar nuevo código") }
            Button(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
                Text("Volver a iniciar sesión")
            }
        }
        OutlinedButton(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth()) {
            Text("Volver")
        }
    }
}
