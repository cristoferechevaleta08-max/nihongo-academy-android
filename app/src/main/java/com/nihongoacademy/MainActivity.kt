package com.nihongoacademy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nihongoacademy.data.AuthRepository
import com.nihongoacademy.data.SessionStore
import com.nihongoacademy.ui.CollectionScreen
import com.nihongoacademy.ui.HomeScreen
import com.nihongoacademy.ui.FlashcardsScreen
import com.nihongoacademy.ui.LanguageSelectionScreen
import com.nihongoacademy.ui.LessonScreen
import com.nihongoacademy.ui.LoginScreen
import com.nihongoacademy.ui.ModuleDetailScreen
import com.nihongoacademy.ui.ModuleExamScreen
import com.nihongoacademy.ui.ModuleOverviewScreen
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
    val context = LocalContext.current
    val authRepository = remember { AuthRepository(context) }

    LaunchedEffect(Unit) {
        if (authRepository.currentUser() != null) {
            val selectedLanguage = SessionStore(context).readLanguage() ?: "ja"
            navController.navigate("home/${selectedLanguage.lowercase()}") {
                popUpTo("welcome") { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screens.Welcome.route
    ) {
        composable(Screens.Welcome.route) { WelcomeScreen(navController) }
        composable(Screens.Login.route) { LoginScreen(navController) }
        composable(Screens.Register.route) { RegisterScreen(navController) }
        composable(Screens.LanguageSelection.route) { LanguageSelectionScreen(navController) }

        composable("home/{languageCode}") { backStackEntry ->
            val code = backStackEntry.arguments?.getString("languageCode") ?: "ja"
            HomeScreen(navController, code)
        }

        composable("modules/{languageCode}") { backStackEntry ->
            val code = backStackEntry.arguments?.getString("languageCode") ?: "ja"
            ModuleOverviewScreen(navController, code)
        }

        composable("module/{languageCode}/{moduleId}") { backStackEntry ->
            val code = backStackEntry.arguments?.getString("languageCode") ?: "ja"
            val moduleId = backStackEntry.arguments?.getString("moduleId") ?: "module_1"
            ModuleDetailScreen(navController, code, moduleId)
        }

        composable("module_exam/{languageCode}/{moduleId}/{examType}") { backStackEntry ->
            val code = backStackEntry.arguments?.getString("languageCode") ?: "ja"
            val moduleId = backStackEntry.arguments?.getString("moduleId") ?: "module_1"
            val examType = backStackEntry.arguments?.getString("examType") ?: "mid"
            ModuleExamScreen(navController, code, moduleId, examType)
        }

        composable("lesson/{languageCode}/{moduleId}/{lessonId}") { backStackEntry ->
            val code = backStackEntry.arguments?.getString("languageCode") ?: "ja"
            val moduleId = backStackEntry.arguments?.getString("moduleId") ?: "module_1"
            val lessonId = backStackEntry.arguments?.getString("lessonId") ?: "m1_l1"
            LessonScreen(navController, code, moduleId, lessonId)
        }

        composable("flashcards/{languageCode}") { backStackEntry ->
            val code = backStackEntry.arguments?.getString("languageCode") ?: "ja"
            FlashcardsScreen(navController, code)
        }

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
