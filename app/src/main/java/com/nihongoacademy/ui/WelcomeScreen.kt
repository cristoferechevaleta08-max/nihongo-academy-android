package com.nihongoacademy.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import com.nihongoacademy.R
import com.nihongoacademy.ui.theme.AcademyColors
import com.nihongoacademy.ui.theme.NihongoTheme

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(AcademyColors.Background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 28.dp, vertical = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(12.dp))
        Image(
            painter = painterResource(R.drawable.bg_japanese_landscape_placeholder),
            contentDescription = "Paisaje japonés con pagoda, montañas y pétalos de sakura",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxWidth().heightIn(min = 176.dp, max = 224.dp)
        )
        Spacer(Modifier.height(18.dp))
        Text(
            text = "Nihongo\nAcademy",
            color = AcademyColors.TextPrimary,
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 36.sp, lineHeight = 39.sp, fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(9.dp))
        Text(
            text = "— 日本語 —",
            color = AcademyColors.Sakura,
            style = MaterialTheme.typography.titleMedium.copy(letterSpacing = 4.sp),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Aprende. Practica. Conecta.",
            color = AcademyColors.TextPrimary,
            style = MaterialTheme.typography.bodyLarge.copy(letterSpacing = 0.5.sp),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(28.dp))
        Button(
            onClick = { navController.navigate("login") },
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AcademyColors.ButtonPrimary,
                contentColor = AcademyColors.ButtonPrimaryText
            )
        ) {
            Text("Iniciar sesión", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        }
        Spacer(Modifier.height(12.dp))
        OutlinedButton(
            onClick = { navController.navigate("register") },
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(14.dp),
            border = BorderStroke(1.dp, AcademyColors.TextPrimary),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = AcademyColors.TextPrimary)
        ) {
            Text("Crear cuenta", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        }
        Spacer(Modifier.height(14.dp))
    }
}

@Preview(name = "Portada · 360 dp", widthDp = 360, heightDp = 800, showBackground = true)
@Composable private fun WelcomePreview360() { NihongoTheme(darkTheme = true) { WelcomeScreen(rememberNavController()) } }

@Preview(name = "Portada · 411 dp", widthDp = 411, heightDp = 900, showBackground = true)
@Composable private fun WelcomePreview411() { NihongoTheme(darkTheme = true) { WelcomeScreen(rememberNavController()) } }

@Preview(name = "Portada · 600 dp", widthDp = 600, heightDp = 960, showBackground = true)
@Composable private fun WelcomePreview600() { NihongoTheme(darkTheme = true) { WelcomeScreen(rememberNavController()) } }
