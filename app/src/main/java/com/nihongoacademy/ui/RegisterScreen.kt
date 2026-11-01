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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nihongoacademy.data.AuthRepository
import com.nihongoacademy.data.AuthResult
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavController) {
    val context = LocalContext.current
    val clipboard = LocalClipboardManager.current
    val repository = remember { AuthRepository(context) }
    val scope = rememberCoroutineScope()
    var visibleName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }
    var recoveryCode by remember { mutableStateOf<String?>(null) }
    var loading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (recoveryCode == null) {
            Text("Crear cuenta", style = MaterialTheme.typography.headlineMedium)
            Text("Tu espacio personal de aprendizaje.", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(top = 6.dp, bottom = 20.dp))
            OutlinedTextField(value = visibleName, onValueChange = { visibleName = it; error = null }, label = { Text("Nombre visible") }, singleLine = true, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(
                value = email,
                onValueChange = { email = it; error = null },
                label = { Text("Correo electrónico") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it; error = null },
                label = { Text("Contraseña (mínimo 8 caracteres)") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
            )
            OutlinedTextField(
                value = confirm,
                onValueChange = { confirm = it; error = null },
                label = { Text("Repetir contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
            )
            error?.let { Text(it, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 10.dp)) }
            Button(
                onClick = {
                    if (password != confirm) {
                        error = "Las contraseñas no coinciden."
                    } else {
                        loading = true
                        error = null
                        scope.launch {
                            when (val result = repository.register(visibleName, email, password)) {
                                is AuthResult.Success -> {
                                    if (result.recoveryCode != null) recoveryCode = result.recoveryCode
                                    else navController.navigate("language") {
                                        popUpTo("welcome") { inclusive = true }
                                    }
                                }
                                is AuthResult.Failure -> error = result.message
                            }
                            loading = false
                        }
                    }
                },
                enabled = !loading && visibleName.isNotBlank() && email.isNotBlank() && password.isNotBlank() && confirm.isNotBlank(),
                modifier = Modifier.fillMaxWidth().padding(top = 18.dp)
            ) {
                if (loading) CircularProgressIndicator()
                else Text("Crear cuenta")
            }
            OutlinedButton(onClick = { navController.navigate("login") }, modifier = Modifier.fillMaxWidth()) {
                Text("Ya tengo una cuenta")
            }
        } else {
            Text("Guarda tu código de recuperación", style = MaterialTheme.typography.headlineSmall)
            Text(
                "Este código permite restablecer tu contraseña si la olvidas. Se muestra una sola vez y no podemos recuperarlo por ti.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 12.dp)
            )
            Card(modifier = Modifier.fillMaxWidth()) {
                Text(
                    recoveryCode.orEmpty(),
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(20.dp)
                )
            }
            Button(
                onClick = { clipboard.setText(AnnotatedString(recoveryCode.orEmpty())) },
                modifier = Modifier.fillMaxWidth().padding(top = 14.dp)
            ) { Text("Copiar código") }
            OutlinedButton(
                onClick = {
                    navController.navigate("language") {
                        popUpTo("welcome") { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Ya lo guardé, continuar") }
        }
    }
}
