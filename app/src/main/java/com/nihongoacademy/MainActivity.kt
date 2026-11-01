package com.nihongoacademy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nihongoacademy.data.AuthRepository
import com.nihongoacademy.data.SessionStore
import com.nihongoacademy.ui.CollectionScreen
import com.nihongoacademy.ui.CommunityScreen
import com.nihongoacademy.ui.ExamScreen
import com.nihongoacademy.ui.FlashcardsScreen
import com.nihongoacademy.ui.HomeScreen
import com.nihongoacademy.ui.LanguageSelectionScreen
import com.nihongoacademy.ui.LessonScreen
import com.nihongoacademy.ui.LoginScreen
import com.nihongoacademy.ui.ModuleDetailScreen
import com.nihongoacademy.ui.ModuleExamScreen
import com.nihongoacademy.ui.ModuleOverviewScreen
import com.nihongoacademy.ui.PasswordResetScreen
import com.nihongoacademy.ui.ProfileScreen
import com.nihongoacademy.ui.RegisterScreen
import com.nihongoacademy.ui.SettingsScreen
import com.nihongoacademy.ui.StudyScreen
import com.nihongoacademy.ui.WelcomeScreen
import com.nihongoacademy.ui.theme.AcademyColors
import com.nihongoacademy.ui.theme.AcademyDimens
import com.nihongoacademy.ui.theme.AcademyShapes
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
    val backStackEntry by navController.currentBackStackEntryAsState()
    val route = backStackEntry?.destination?.route.orEmpty()
    var activeLanguage by rememberSaveable { mutableStateOf("ja") }

    LaunchedEffect(route) {
        val routeLanguage = backStackEntry?.arguments?.getString("languageCode")
        if (!routeLanguage.isNullOrBlank()) activeLanguage = routeLanguage.lowercase()
    }

    val showBottomBar = route.startsWith("home/") ||
        route.startsWith("modules/") ||
        route == "community" ||
        route == Screens.Profile.route

    LaunchedEffect(Unit) {
        if (authRepository.currentUser() != null) {
            val selectedLanguage = SessionStore(context).readLanguage() ?: "ja"
            activeLanguage = selectedLanguage.lowercase()
            navController.navigate("home/${activeLanguage}") {
                popUpTo(Screens.Welcome.route) { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = AcademyColors.Background,
        bottomBar = {
            if (showBottomBar) {
                AcademyBottomBar(
                    navController = navController,
                    currentRoute = route,
                    languageCode = activeLanguage
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.Welcome.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screens.Welcome.route) { WelcomeScreen(navController) }
            composable(Screens.Login.route) { LoginScreen(navController) }
            composable(Screens.Register.route) { RegisterScreen(navController) }
            composable("password_reset") { PasswordResetScreen(navController) }
            composable(Screens.LanguageSelection.route) { LanguageSelectionScreen(navController) }

            composable("home/{languageCode}") { entry ->
                val code = entry.arguments?.getString("languageCode") ?: "ja"
                HomeScreen(navController, code)
            }
            composable("modules/{languageCode}") { entry ->
                val code = entry.arguments?.getString("languageCode") ?: "ja"
                ModuleOverviewScreen(navController, code)
            }
            composable("community") { CommunityScreen(navController) }
            composable("exams") { ExamScreen(navController) }
            composable("module/{languageCode}/{moduleId}") { entry ->
                val code = entry.arguments?.getString("languageCode") ?: "ja"
                val moduleId = entry.arguments?.getString("moduleId") ?: "module_1"
                ModuleDetailScreen(navController, code, moduleId)
            }
            composable("module_exam/{languageCode}/{moduleId}/{examType}") { entry ->
                val code = entry.arguments?.getString("languageCode") ?: "ja"
                val moduleId = entry.arguments?.getString("moduleId") ?: "module_1"
                val examType = entry.arguments?.getString("examType") ?: "mid"
                ModuleExamScreen(navController, code, moduleId, examType)
            }
            composable("lesson/{languageCode}/{moduleId}/{lessonId}") { entry ->
                val code = entry.arguments?.getString("languageCode") ?: "ja"
                val moduleId = entry.arguments?.getString("moduleId") ?: "module_1"
                val lessonId = entry.arguments?.getString("lessonId") ?: "m1_l1"
                LessonScreen(navController, code, moduleId, lessonId)
            }
            composable("flashcards/{languageCode}") { entry ->
                val code = entry.arguments?.getString("languageCode") ?: "ja"
                FlashcardsScreen(navController, code)
            }
            composable(Screens.Study.route) { StudyScreen(navController) }
            composable(Screens.Profile.route) { ProfileScreen(navController) }
            composable(Screens.Settings.route) { SettingsScreen(navController) }
            composable(Screens.Collection.route) { CollectionScreen(navController) }
        }
    }
}

private data class MainNavigationItem(
    val routeKey: String,
    val label: String,
    val symbol: String
)

private val mainNavigationItems = listOf(
    MainNavigationItem("home", "Inicio", "⌂"),
    MainNavigationItem("courses", "Cursos", "▤"),
    MainNavigationItem("community", "Comunidad", "◎"),
    MainNavigationItem("profile", "Perfil", "◉")
)

@Composable
private fun AcademyBottomBar(
    navController: NavHostController,
    currentRoute: String,
    languageCode: String
) {
    val selectedRoute = when {
        currentRoute.startsWith("home/") -> "home"
        currentRoute.startsWith("modules/") -> "courses"
        currentRoute == "community" -> "community"
        currentRoute == Screens.Profile.route -> "profile"
        else -> ""
    }

    NavigationBar(
        containerColor = AcademyColors.Surface,
        contentColor = AcademyColors.TextPrimary,
        tonalElevation = AcademyDimens.Space1
    ) {
        mainNavigationItems.forEach { item ->
            val selected = selectedRoute == item.routeKey
            NavigationBarItem(
                selected = selected,
                onClick = {
                    val destination = when (item.routeKey) {
                        "home" -> "home/$languageCode"
                        "courses" -> "modules/$languageCode"
                        "community" -> "community"
                        else -> Screens.Profile.route
                    }
                    navController.navigate(destination) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Surface(
                        shape = AcademyShapes.Pill,
                        color = if (selected) AcademyColors.Sakura.copy(alpha = 0.14f)
                        else AcademyColors.Surface,
                        modifier = Modifier.padding(top = AcademyDimens.Space1)
                    ) {
                        Text(
                            text = item.symbol,
                            modifier = Modifier.padding(
                                horizontal = AcademyDimens.Space4,
                                vertical = AcademyDimens.Space1
                            ),
                            color = if (selected) AcademyColors.Sakura
                            else AcademyColors.TextSecondary,
                            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
                        )
                    }
                },
                label = {
                    Text(
                        text = item.label,
                        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
                    )
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = AcademyColors.Sakura,
                    selectedTextColor = AcademyColors.TextPrimary,
                    indicatorColor = AcademyColors.Surface,
                    unselectedIconColor = AcademyColors.TextSecondary,
                    unselectedTextColor = AcademyColors.TextSecondary
                )
            )
        }
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
