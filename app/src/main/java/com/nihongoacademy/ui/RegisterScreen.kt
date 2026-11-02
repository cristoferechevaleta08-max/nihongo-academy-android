package com.nihongoacademy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import com.nihongoacademy.data.AuthRepository
import com.nihongoacademy.data.AuthResult
import com.nihongoacademy.ui.theme.AcademyColors
import com.nihongoacademy.ui.theme.NihongoTheme
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavController) {
    val context = LocalContext.current
    val clipboard = LocalClipboardManager.current
    val repository = remember { AuthRepository(context) }
    val scope = rememberCoroutineScope()
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    var recoveryCode by remember { mutableStateOf<String?>(null) }
    var loading by remember { mutableStateOf(false) }

    val validEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
    val formValid = username.isNotBlank() && validEmail && password.length >= 8 &&
        confirm.isNotBlank() && password == confirm

    Box(
        modifier = Modifier.fillMaxSize().background(AcademyColors.Background)
    ) {
        if (recoveryCode == null) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .imePadding()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(Modifier.fillMaxWidth().height(48.dp), contentAlignment = Alignment.Center) {
                    Text(
                        "‹",
                        color = AcademyColors.TextPrimary,
                        fontSize = 38.sp,
                        modifier = Modifier.align(Alignment.CenterStart).clickable { navController.popBackStack() }
                            .padding(horizontal = 8.dp)
                    )
                    Text(
                        "Crear cuenta",
                        color = AcademyColors.TextPrimary,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }
                Spacer(Modifier.height(5.dp))
                Text(
                    "Únete a nuestra comunidad de aprendices.",
                    color = AcademyColors.TextSecondary,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(25.dp))
                RegisterField(
                    value = username, onValueChange = { username = it; error = null },
                    label = "Nombre de usuario", leading = "♙",
                    keyboardType = KeyboardType.Text
                )
                Spacer(Modifier.height(12.dp))
                RegisterField(
                    value = email, onValueChange = { email = it; error = null },
                    label = "Correo electrónico", leading = "✉",
                    keyboardType = KeyboardType.Email
                )
                Spacer(Modifier.height(12.dp))
                RegisterField(
                    value = password, onValueChange = { password = it; error = null },
                    label = "Contraseña", leading = "▣",
                    keyboardType = KeyboardType.Password,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailing = {
                        Text(
                            if (passwordVisible) "◉" else "◎",
                            color = AcademyColors.TextSecondary,
                            modifier = Modifier.clickable { passwordVisible = !passwordVisible }.padding(8.dp)
                        )
                    }
                )
                Spacer(Modifier.height(12.dp))
                RegisterField(
                    value = confirm, onValueChange = { confirm = it; error = null },
                    label = "Confirmar contraseña", leading = "▣",
                    keyboardType = KeyboardType.Password,
                    visualTransformation = PasswordVisualTransformation()
                )
                error?.let {
                    Text(it, color = MaterialTheme.colorScheme.error, modifier = Modifier.fillMaxWidth().padding(top = 10.dp))
                }
                Button(
                    onClick = {
                        if (password != confirm) {
                            error = "Las contraseñas no coinciden."
                        } else if (password.length < 8) {
                            error = "La contraseña debe tener al menos 8 caracteres."
                        } else if (!validEmail) {
                            error = "Introduce un correo electrónico válido."
                        } else {
                            loading = true
                            error = null
                            scope.launch {
                                when (val result = repository.register(username, email, password)) {
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
                    enabled = !loading && formValid,
                    modifier = Modifier.fillMaxWidth().padding(top = 20.dp).height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AcademyColors.ButtonPrimary,
                        contentColor = AcademyColors.ButtonPrimaryText,
                        disabledContainerColor = AcademyColors.ButtonPrimary.copy(alpha = 0.42f),
                        disabledContentColor = AcademyColors.ButtonPrimaryText.copy(alpha = 0.62f)
                    )
                ) {
                    if (loading) CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = AcademyColors.ButtonPrimaryText,
                        strokeWidth = 2.dp
                    ) else Text("Registrarse", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                }
                Spacer(Modifier.height(17.dp))
                Text(
                    text = androidx.compose.ui.text.buildAnnotatedString {
                        pushStyle(androidx.compose.ui.text.SpanStyle(color = AcademyColors.TextSecondary))
                        append("Al registrarte aceptas nuestros ")
                        pop()
                        pushStyle(androidx.compose.ui.text.SpanStyle(color = AcademyColors.Sakura, fontWeight = FontWeight.Medium))
                        append("Términos")
                        pop()
                        pushStyle(androidx.compose.ui.text.SpanStyle(color = AcademyColors.TextSecondary))
                        append(" y ")
                        pop()
                        pushStyle(androidx.compose.ui.text.SpanStyle(color = AcademyColors.Sakura, fontWeight = FontWeight.Medium))
                        append("Política de Privacidad")
                        pop()
                    },
                    style = MaterialTheme.typography.bodySmall.copy(textAlign = TextAlign.Center, lineHeight = 18.sp),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(22.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("¿Ya tienes cuenta? ", color = AcademyColors.TextSecondary, style = MaterialTheme.typography.bodyMedium)
                    Text(
                        "Inicia sesión",
                        color = AcademyColors.Sakura,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                        modifier = Modifier.clickable { navController.navigate("login") }
                    )
                }
                Spacer(Modifier.height(22.dp))
                RegisterPagodaDecoration(Modifier.fillMaxWidth().height(96.dp).alpha(0.26f))
                Spacer(Modifier.height(8.dp))
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Guarda tu código de recuperación", color = AcademyColors.TextPrimary, style = MaterialTheme.typography.headlineSmall)
                Text(
                    "Este código permite restablecer tu contraseña si la olvidas. Se muestra una sola vez y no podemos recuperarlo por ti.",
                    color = AcademyColors.TextSecondary,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = AcademyColors.Surface),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text(recoveryCode.orEmpty(), color = AcademyColors.TextPrimary, style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(20.dp))
                }
                Button(
                    onClick = { clipboard.setText(AnnotatedString(recoveryCode.orEmpty())) },
                    modifier = Modifier.fillMaxWidth().padding(top = 14.dp).height(52.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AcademyColors.ButtonPrimary, contentColor = AcademyColors.ButtonPrimaryText)
                ) { Text("Copiar código") }
                OutlinedButton(
                    onClick = {
                        navController.navigate("language") { popUpTo("welcome") { inclusive = true } }
                    },
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = AcademyColors.TextPrimary)
                ) { Text("Ya lo guardé, continuar") }
            }
        }
    }
}

@Composable
private fun RegisterField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leading: String,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailing: (@Composable () -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth().heightIn(min = 58.dp),
        singleLine = true,
        shape = RoundedCornerShape(14.dp),
        placeholder = { Text(label, color = AcademyColors.TextSecondary) },
        leadingIcon = {
            Text(leading, color = AcademyColors.TextSecondary, fontSize = 19.sp, modifier = Modifier.padding(start = 2.dp))
        },
        trailingIcon = trailing,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = AcademyColors.TextPrimary,
            unfocusedTextColor = AcademyColors.TextPrimary,
            focusedContainerColor = AcademyColors.Surface,
            unfocusedContainerColor = AcademyColors.Surface,
            focusedBorderColor = AcademyColors.Sakura,
            unfocusedBorderColor = AcademyColors.Border,
            cursorColor = AcademyColors.Sakura,
            focusedPlaceholderColor = AcademyColors.TextSecondary,
            unfocusedPlaceholderColor = AcademyColors.TextSecondary
        )
    )
}

@Composable
private fun RegisterPagodaDecoration(modifier: Modifier = Modifier) {
    androidx.compose.foundation.Canvas(modifier) {
        val w = size.width
        val h = size.height
        val ink = AcademyColors.TextSecondary.copy(alpha = 0.8f)
        val pink = AcademyColors.Sakura.copy(alpha = 0.75f)
        val centerX = w * 0.5f
        val baseY = h * 0.92f
        drawLine(ink, androidx.compose.ui.geometry.Offset(w * 0.12f, baseY), androidx.compose.ui.geometry.Offset(w * 0.88f, baseY), 1.dp.toPx())
        for (level in 0..2) {
            val y = h * (0.30f + level * 0.20f)
            val half = w * (0.12f - level * 0.025f)
            val roof = androidx.compose.ui.graphics.Path().apply {
                moveTo(centerX - half, y + h * 0.09f)
                lineTo(centerX, y)
                lineTo(centerX + half, y + h * 0.09f)
                lineTo(centerX + half * 0.82f, y + h * 0.075f)
                lineTo(centerX - half * 0.82f, y + h * 0.075f)
                close()
            }
            drawPath(roof, ink)
            drawRect(ink, androidx.compose.ui.geometry.Offset(centerX - half * 0.62f, y + h * 0.09f), Size(half * 1.24f, h * 0.12f))
        }
        drawLine(ink, androidx.compose.ui.geometry.Offset(centerX, h * 0.08f), androidx.compose.ui.geometry.Offset(centerX, h * 0.29f), 1.dp.toPx())
        val petals = listOf(
            androidx.compose.ui.geometry.Offset(w * 0.18f, h * 0.26f),
            androidx.compose.ui.geometry.Offset(w * 0.78f, h * 0.18f),
            androidx.compose.ui.geometry.Offset(w * 0.84f, h * 0.66f),
            androidx.compose.ui.geometry.Offset(w * 0.24f, h * 0.72f)
        )
        petals.forEach { drawCircle(pink, 3.dp.toPx(), it) }
    }
}

@Preview(name = "Crear cuenta · 360 dp", widthDp = 360, heightDp = 800, showBackground = true)
@Composable private fun RegisterPreview360() { NihongoTheme(darkTheme = true) { RegisterScreen(rememberNavController()) } }

@Preview(name = "Crear cuenta · 411 dp", widthDp = 411, heightDp = 900, showBackground = true)
@Composable private fun RegisterPreview411() { NihongoTheme(darkTheme = true) { RegisterScreen(rememberNavController()) } }

@Preview(name = "Crear cuenta · 600 dp", widthDp = 600, heightDp = 960, showBackground = true)
@Composable private fun RegisterPreview600() { NihongoTheme(darkTheme = true) { RegisterScreen(rememberNavController()) } }
