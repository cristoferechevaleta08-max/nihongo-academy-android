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
import com.nihongoacademy.ui.ProfileScreen
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
    NavHost(
        navController = navController,
        startDestination = Screens.Welcome.route
    ) {
        composable(Screens.Welcome.route) { WelcomeScreen(navController) }
        composable(Screens.Login.route) { LoginScreen(navController) }
        composable(Screens.Register.route) { RegisterScreen(navController) }
        composable(Screens.LanguageSelection.route) { LanguageSelectionScreen(navController) }
        composable(Screens.Home.route) { HomeScreen(navController) }
        composable(Screens.Study.route) { StudyScreen(navController) }
        composable(Screens.Profile.route) { ProfileScreen(navController) }
        composable(Screens.Settings.route) { SettingsScreen(navController) }
        composable(Screens.Collection.route) { CollectionScreen(navController) }
    }
}

sealed class Screens(val route: String) {
    data object Welcome : Screens("welcome")
    data object Login : Screens("login")
    data object Register : Screens("register")
    data object LanguageSelection : Screens("language")
    data object Home : Screens("home")
    data object Study : Screens("study")
    data object Profile : Screens("profile")
    data object Settings : Screens("settings")
    data object Collection : Screens("collection")
}
