package com.nihongoacademy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nihongoacademy.ui.CollectionScreen
import com.nihongoacademy.ui.HomeScreen
import com.nihongoacademy.ui.LanguageSelectionScreen
import com.nihongoacademy.ui.LoginScreen
import com.nihongoacademy.ui.RegisterScreen
import com.nihongoacademy.ui.SettingsScreen
import com.nihongoacademy.ui.StudyScreen
import com.nihongoacademy.ui.WelcomeScreen
import com.nihongoacademy.ui.theme.NihongoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NihongoTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNavHost()
                }
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("language") { LanguageSelectionScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("study") { StudyScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
        composable("collection") { CollectionScreen(navController) }
    }
}
