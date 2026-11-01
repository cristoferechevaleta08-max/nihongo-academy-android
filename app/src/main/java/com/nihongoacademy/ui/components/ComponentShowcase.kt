package com.nihongoacademy.ui.components
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nihongoacademy.ui.theme.*
@Composable private fun Showcase(){Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(AcademyDimens.Space5),verticalArrangement=Arrangement.spacedBy(AcademyDimens.Space4)){Text("Sistema visual",color=AcademyColors.TextPrimary,style=MaterialTheme.typography.headlineLarge);PrimaryButton("Acción principal",{});SecondaryButton("Acción secundaria",{});TextFieldLabeled("Correo electrónico","",{},placeholder="nombre@correo.com");Row(horizontalArrangement=Arrangement.spacedBy(AcademyDimens.Space2)){AcademyChip("Seleccionado",true);AcademyChip("Normal",false)};AcademyProgressBar(.68f,Modifier.fillMaxWidth());Row(horizontalArrangement=Arrangement.spacedBy(AcademyDimens.Space4)){AcademyProgressRing(.68f,"68%",Modifier.size(88.dp));AcademyCard(Modifier.weight(1f)){Column(Modifier.padding(AcademyDimens.Space4)){Text("Tarjeta",color=AcademyColors.TextPrimary,style=MaterialTheme.typography.titleMedium);Text("Superficie elevada",color=AcademyColors.TextSecondary,style=MaterialTheme.typography.bodySmall)}}};AcademySkeleton(Modifier.fillMaxWidth());ToastBanner("Lección guardada");EmptyState("Aún no tienes flashcards","Explora tus cursos para comenzar a practicar.","Explorar cursos",{})}}
@Preview(name="Componentes · 360 dp",widthDp=360,heightDp=900,showBackground=true) @Composable private fun Preview360(){NihongoTheme(true){Showcase()}}
@Preview(name="Componentes · 411 dp",widthDp=411,heightDp=900,showBackground=true) @Composable private fun Preview411(){NihongoTheme(true){Showcase()}}
@Preview(name="Componentes · 600 dp",widthDp=600,heightDp=960,showBackground=true) @Composable private fun Preview600(){NihongoTheme(true){Showcase()}}
