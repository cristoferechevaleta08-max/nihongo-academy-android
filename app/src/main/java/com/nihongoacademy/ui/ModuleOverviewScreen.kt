package com.nihongoacademy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nihongoacademy.model.LanguageCatalogFactory

@Composable
fun ModuleOverviewScreen(navController: NavController, languageCode: String = "JA") {
    val language = LanguageCatalogFactory.buildSupportedLanguages()
        .firstOrNull { it.code == languageCode }
        ?: LanguageCatalogFactory.buildSupportedLanguages().first()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Text("${language.name} · Módulos", style = MaterialTheme.typography.headlineMedium)
        Text("10 módulos · 2 integradores · 2 exámenes sorpresa por módulo", style = MaterialTheme.typography.bodyLarge)

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(language.modules) { module ->
                ModuleCard(
                    module = module,
                    onClick = { navController.navigate("module/${module.id}") }
                )
            }
        }
    }
}

@Composable
private fun ModuleCard(
    module: com.nihongoacademy.model.LanguageModule,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Módulo ${module.number}", style = MaterialTheme.typography.labelLarge)
                if (module.isIntegrative) {
                    Text("Integrador", style = MaterialTheme.typography.labelLarge)
                }
            }
            Text(module.title, style = MaterialTheme.typography.titleLarge)
            Text(module.targetLevel, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text("• ${module.lessons.size} lecciones")
            Text("• Examen sorpresa a la mitad")
            Text("• Examen sorpresa final")
        }
    }
}
