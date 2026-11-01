package com.nihongoacademy.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nihongoacademy.data.AuthRepository
import com.nihongoacademy.data.PreferencesStore
import com.nihongoacademy.data.StudySettings
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(navController: NavController) {
    val context = LocalContext.current
    val authRepository = remember { AuthRepository(context) }
    val preferencesStore = remember { PreferencesStore(context) }
    val scope = rememberCoroutineScope()
    val settingsFlow = remember(preferencesStore) { preferencesStore.studySettingsFlow() }
    val settings by settingsFlow.collectAsState(initial = StudySettings())
    val themeModeFlow = remember(preferencesStore) { preferencesStore.themeModeFlow() }
    val themeMode by themeModeFlow.collectAsState(initial = "system")
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(100)
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = if (settings.reduceMotion) EnterTransition.None else fadeIn() + slideInVertically(initialOffsetY = { 16 }),
        exit = if (settings.reduceMotion) ExitTransition.None else fadeOut() + slideOutVertically(targetOffsetY = { 16 })
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Configuración", style = MaterialTheme.typography.headlineMedium)

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("Apariencia", style = MaterialTheme.typography.titleMedium)
                    Text("Elige cómo se muestra Nihongo Academy.", style = MaterialTheme.typography.bodyMedium)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        if (themeMode == "system") Button(onClick = {}, modifier = Modifier.weight(1f)) { Text("Sistema") }
                        else OutlinedButton(onClick = { scope.launch { preferencesStore.setThemeMode("system") } }, modifier = Modifier.weight(1f)) { Text("Sistema") }

                        if (themeMode == "light") Button(onClick = {}, modifier = Modifier.weight(1f)) { Text("Claro") }
                        else OutlinedButton(onClick = { scope.launch { preferencesStore.setThemeMode("light") } }, modifier = Modifier.weight(1f)) { Text("Claro") }

                        if (themeMode == "dark") Button(onClick = {}, modifier = Modifier.weight(1f)) { Text("Oscuro") }
                        else OutlinedButton(onClick = { scope.launch { preferencesStore.setThemeMode("dark") } }, modifier = Modifier.weight(1f)) { Text("Oscuro") }
                    }
                }
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(18.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Ayuda de pronunciación")
                        Text("Muestra controles de audio cuando hay una voz instalada.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    Switch(
                        checked = settings.showPronunciation,
                        onCheckedChange = { enabled -> scope.launch { preferencesStore.setShowPronunciation(enabled) } }
                    )
                }
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(18.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Reducir animaciones")
                        Text("Desactiva las transiciones decorativas.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    Switch(
                        checked = settings.reduceMotion,
                        onCheckedChange = { enabled -> scope.launch { preferencesStore.setReduceMotion(enabled) } }
                    )
                }
            }

            Button(
                onClick = {
                    scope.launch {
                        authRepository.logout()
                        navController.navigate("welcome") { popUpTo(0) { inclusive = true } }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cerrar sesión")
            }
        }
    }
}
