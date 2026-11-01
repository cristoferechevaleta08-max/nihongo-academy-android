package com.nihongoacademy.ui.theme
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.nihongoacademy.data.PreferencesStore
private val DarkColors=darkColorScheme(primary=AcademyColors.ButtonPrimary,onPrimary=AcademyColors.ButtonPrimaryText,secondary=AcademyColors.Sakura,onSecondary=AcademyColors.Background,tertiary=AcademyColors.JapaneseRed,onTertiary=AcademyColors.TextPrimary,background=AcademyColors.Background,onBackground=AcademyColors.TextPrimary,surface=AcademyColors.Surface,onSurface=AcademyColors.TextPrimary,surfaceVariant=AcademyColors.SurfaceElevated,onSurfaceVariant=AcademyColors.TextSecondary,outline=AcademyColors.Border,error=AcademyColors.JapaneseRed,onError=AcademyColors.TextPrimary)
private val LightColors=lightColorScheme(primary=Color(0xFF9B5268),onPrimary=Color.White,secondary=Color(0xFF9B5268),onSecondary=Color.White,tertiary=AcademyColors.JapaneseRed,background=Color(0xFFF7F4F2),onBackground=Color(0xFF171719),surface=Color.White,onSurface=Color(0xFF171719),surfaceVariant=Color(0xFFECE8E7),onSurfaceVariant=Color(0xFF66636A),outline=Color(0xFFD6D0D1),error=AcademyColors.JapaneseRed)
@Composable fun NihongoTheme(darkTheme:Boolean?=null,content:@Composable()->Unit) {
 val context=LocalContext.current
 val mode by remember(context){PreferencesStore(context).themeModeFlow()}.collectAsState(initial="dark")
 val dark=darkTheme?:when(mode){"light"->false;"dark"->true;else->isSystemInDarkTheme()}
 MaterialTheme(colorScheme=if(dark) DarkColors else LightColors,typography=AcademyTypography,shapes=AcademyMaterialShapes,content=content)
}